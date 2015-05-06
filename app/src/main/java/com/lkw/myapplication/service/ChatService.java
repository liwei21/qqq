package com.lkw.myapplication.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.lkw.myapplication.ChatActivity;
import com.lkw.myapplication.R;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LKW on 2015/5/3.
 */
public class ChatService extends Service {

    public ChatService() {
    }
    //        1.创建XMPP连接    HttpURLConnection
//                          XMPPTCPConnection

    private static XMPPTCPConnection conn;

    public class ChatController extends Binder {

        private Collection<RosterEntry> entries;

        /**
         * 停止监听器，不再接收消息，对于外部界面而言
         * @param listener
         */
        public void removePackerListener (PacketListener listener){
            if (listener!=null){
                if (conn!=null){
                    conn.removePacketListener(listener);
                }
            }

        }

        /**
         * 添加监听器接口，外部界面接收消息的时候，设置
         * @param listener
         */
        public void addPacketListener(PacketListener listener){
            if (listener!=null){
                if (conn!=null){
                    conn.addPacketListener(listener,new MessageTypeFilter(Message.Type.chat));
                }
            }
        }

        /**
         * 添加联系人
         * @param target
         */
        public void addFriend(String target){
            if(conn!=null){

                Roster roster = conn.getRoster();
                try {
                    roster.createEntry(target, "张", null);
                } catch (SmackException.NotLoggedInException e) {
                    e.printStackTrace();
                } catch (SmackException.NoResponseException e) {
                    e.printStackTrace();
                } catch (XMPPException.XMPPErrorException e) {
                    e.printStackTrace();
                } catch (SmackException.NotConnectedException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * 注册账号
         * @param username
         * @param password
         */
        public boolean Register(String username,String password){

            boolean isOK= false;
            if(username!=null&&password!=null){
                if (conn!=null){
                    //账号注册
                    //获取账号管理权，进行注册操作
                    AccountManager accountManager = AccountManager.getInstance(conn);

                    try {
                        accountManager.createAccount(username, password);
                        isOK=true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    isOK=false;
                }
            }
            return isOK;
        }



        /**
         * 用于开启聊天会话，主要在 ChatActivity 使用，用于发送和接收消息
         *
         * @param target   需要和谁聊天
         * @param thread1
         *@param listener MessageListener 用来监听消息  @return Chat 对象，可以通过Chat 调用 sendMessage 发送消息
         */
        public Chat openChat(String target, String thread1, MessageListener listener) {
            Chat ret = null;

            if (target != null) {
                if (conn != null) {

                    if (conn.isAuthenticated()) {

                        //已经登录的情况

                        ChatManager chatManager = ChatManager.getInstanceFor(conn);
                        //创建聊天会话
                        ret = chatManager.createChat(target,thread1, listener);
                    }
                }
            }

            return ret;
        }

        /**
         * 给外部的LoginActivity提供直接调用的功能
         *
         * @param userName
         * @param password
         * @return
         */
        public String login(String userName, String password) {
            String ret = null;
            if (userName != null && password != null) {
                if (conn != null) {
                    try {
                        conn.login(userName, password);
                        ret = conn.getUser();


//                        Roster roster = conn.getRoster();
//                        roster.createEntry("zdf@10.0.154.195", "张", null);
                    } catch (XMPPException e) {
                        e.printStackTrace();
                    } catch (SmackException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return ret;
        }

        //获取当前登录账号中的所有联系人信息
        public List<RosterEntry> getRosterEntries() {
            List<RosterEntry> ret = null;

            if (conn != null) {
                //如果当前已经登录过，那么获取
                if (conn.isAuthenticated()) {
                    Roster roster = conn.getRoster();
                    if (roster != null) {
                        //获取联系人列表
                        entries = roster.getEntries();

                        ret = new LinkedList<RosterEntry>();
                        //将联系人取出来
                        ret.addAll(entries);


                    }
                }
            }

            return ret;
        }


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new ChatController();
    }

    private ChatThread thread;

    @Override
    public void onCreate() {
        super.onCreate();
        if (conn != null) {
            try {
                conn.disconnect();
            } catch (SmackException.NotConnectedException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        //连接服务器
        conn = new XMPPTCPConnection("10.0.154.11");


    }

    /**
     * 服务的启动
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        启动线程
        if (thread == null) {
            thread = new ChatThread();
            thread.start();
        }

        return START_STICKY;
    }


    @Override
    public void onDestroy() {

        if (thread != null) {
            thread.stopThread();
            thread = null;
        }
        super.onDestroy();
    }

    /**
     * 实际的聊天的线程部分
     */
    class ChatThread extends Thread {

        private boolean running;

        public void stopThread() {
            running = false;
        }

        @Override
        public void run() {
            running = true;
            try {
                // Smark API当中，大部分方法发生错误的时候，直接抛异常
                conn.connect();


                // TODO 接收消息
                //向连接中添加数据包的监听器，当服务器给客户端转发消息的时候
                // XMPPTCPConnection 会自动钓鱼PackerListener的回调
                // 参数1.数据包监听器，用于处理数据
                // 参数2.监听器要监听哪些类型的数据
                // 因为conn内部所有的操作都是数据包，例如，获取联系人，其实也是在发送数据包

                PacketListener packetListener = new PacketListener() {
                    @Override
                    public void processPacket(Packet packet) throws SmackException.NotConnectedException {
                        //TODO 处理消息类型的数据包
                        //因为Message 类型继承了Packet，所有检查是否是Message
                        if (packet instanceof Message) {
                            Message msg = (Message) packet;
                            //消息内容
                            String body = msg.getBody();
                            //会话的主题
                            String subject = msg.getSubject();
                            //从谁发过来的
                            String from = msg.getFrom();
                            //发给谁
                            String to = msg.getTo();

                            //聊天会话，通过这个主题，就可以确定发送者创建的 Chat对象
                            //这个thread类似于对讲机直接的联系
                            String thread1 = msg.getThread();

                            Log.d("ChatThread", "packet from :" + from + "to:" + to + "content:" + body);

                            //TODO 当收到消息，就模拟一下QQ的通知栏信息

                            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(ChatService.this);
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(ChatService.this);
                            builder.setContentTitle("您有新消息");
                            builder.setContentText(body);
                            builder.setSmallIcon(R.drawable.ic_launcher);

                            //设置点击之后直接进入聊天
                            Intent chatIntent = new Intent(getApplicationContext(), ChatActivity.class);
                            //如果应用启动了，并且ChatActivity在任务栈中，那么，直接启动
                            //如果没有启动，那么开一个新的栈

                            chatIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            //使用userJID给谁回复

                            chatIntent.putExtra("userJID",from);
                            //主题标识，进行两个账户之间的联系
                            chatIntent.putExtra("thread1",thread1);

                            chatIntent.putExtra("body",body);

                            PendingIntent pendingIntent = PendingIntent.getActivity(ChatService.this, 998, chatIntent, PendingIntent.FLAG_ONE_SHOT);


                            builder.setContentIntent(pendingIntent);

                            Notification notification = builder.build();
                            //发送通知
                            managerCompat.notify((int)(System.currentTimeMillis()),notification);
                        }
                    }
                };
                // ！！！ 在开始会话之前，进行PackageListener的设置
                conn.addPacketListener(packetListener, new MessageTypeFilter(Message.Type.chat));


                //进行循环,等待消息，以及进行发送处理
                while (running) {
                    Thread.sleep(300);
                }

            } catch (SmackException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XMPPException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.disconnect();
                    } catch (SmackException.NotConnectedException e) {
                        e.printStackTrace();
                    }
                    conn = null;
                }
            }
        }
    }

}
