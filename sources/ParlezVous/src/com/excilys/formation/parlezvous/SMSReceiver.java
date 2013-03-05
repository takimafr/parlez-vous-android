package com.excilys.formation.parlezvous;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		 	Bundle bundle = intent.getExtras();        
	        StringBuilder sb = new StringBuilder(); 
	        if (bundle != null) {
	            for (Object pdu : (Object[]) bundle.get("pdus")) {
	                SmsMessage sms = SmsMessage.createFromPdu((byte[])pdu);                 
	                sb.append("SMS received from ") //
	                .append(sms.getOriginatingAddress()) //
	                .append(" : ") //
	                .append(sms.getMessageBody()) //
	                .append("\n");
	            }
	        }
		Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
	}
}