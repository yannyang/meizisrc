package mobidever.godutch.receiver;

import mobidever.godutch.R;
import mobidever.godutch.activity.ActivityMain;
import mobidever.godutch.service.ServiceDatabaseBackup;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverDatabaseBackup extends BroadcastReceiver {

	NotificationManager m_NotificationManager;   
    Notification m_Notification;   
       
    Intent m_Intent;   
    PendingIntent m_PendingIntent;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		m_NotificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);          
		Log.i("GoDutch", "�㲥��" + (intent.getLongExtra("Date",0)/1000));
        //���֪ͨʱת������   
        m_Intent=new Intent(context,ActivityMain.class);   
           
        m_PendingIntent=PendingIntent.getActivity(context, 0, m_Intent, 0);   
           
        m_Notification=new Notification();   
        
        //����֪ͨ��״̬����ʾ��ͼ��   
        m_Notification.icon=R.drawable.icon;   
           
        //�����ǵ��֪ͨʱ��ʾ������   
        m_Notification.tickerText="AA����С������ִ�����ݱ���";   
                           
        //֪ͨʱ������Ĭ������   
        m_Notification.defaults=Notification.DEFAULT_SOUND;   
           
        //����֪ͨ��ʾ�Ĳ���   
        m_Notification.setLatestEventInfo(context, "AA����С������ִ�����ݱ���", "AA����С������ִ�����ݱ���",m_PendingIntent);
        
        //����������Ϊ��ʼִ�����֪ͨ   
        m_NotificationManager.notify(0,m_Notification);
        
        Intent _Intent = new Intent(context, ServiceDatabaseBackup.class);
        context.startService(_Intent);
	}

}
