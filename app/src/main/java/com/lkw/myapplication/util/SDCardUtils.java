package com.lkw.myapplication.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.os.StatFs;

public class SDCardUtils {
	/**�ж�SDCard�Ƿ����*/
	public static boolean isSDCardOk(){
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)){
			return true;
		}
		return false;
	}
	/**��ȡsdcard��������·��*/
	public static String getSDCardPath(){
		if(isSDCardOk()){
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}
	/**��ȡsdcard��ȫ���Ŀռ��С������MB�ֽ�*/
	public static long getTotalSize(){
		if(isSDCardOk()){
			StatFs sf=new StatFs(getSDCardPath());
			//��ȡ�ܿ���
			int count = sf.getBlockCount();
			//��ȡÿһ��Ĵ�С
			int blockSize = sf.getBlockSize();
			
			long totalSize=count*blockSize/1024/1024;
			
			return totalSize;
		}
		return 0;
	}
	/**��ȡsdcard��ʣ��Ŀ��ÿռ�Ĵ�С������MB�ֽ�*/
	public static long getAvailableSize(){
		if(isSDCardOk()){
			StatFs sf=new StatFs(getSDCardPath());
			//��ȡ���õĿ���
			int blocks = sf.getAvailableBlocks();
			//��ȡÿһ��Ĵ�С
			int size = sf.getBlockSize();
			
			return blocks*size/1024/1024;
		}
		return 0;
	}
	
	/**�����ݣ�byte[]�������sdcardָ����·����*/
	
	public static boolean saveData(byte[] data,String dir,String fileName){
		if(isSDCardOk()){
			//�ж�Ҫ�洢�ĸ�Ŀ¼�Ƿ����
			File fileP=new File(getSDCardPath(),dir);
			if(!fileP.exists()){//������---������·��
				fileP.mkdirs();
			}
			//Ŀ���ļ�
			File file=new File(fileP,fileName);
			
			FileOutputStream fos=null;
			try {
				fos = new FileOutputStream(file);
				fos.write(data);
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(fos!=null){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return false;
	}
	
	/**��֪�ļ���·������sdcard�л�ȡ�����ļ�������byte[]*/
	public static byte[] getDataFromSDCard(String path){
		File file=new File(path);
		if(file.exists()){//�ж��ļ��Ƿ����
			try {
				FileInputStream fis=new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				byte[] buf=new byte[1024];
				int len;
				
				while((len=fis.read(buf))!=-1){
					baos.write(buf, 0, len);
				}
				return baos.toByteArray();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
