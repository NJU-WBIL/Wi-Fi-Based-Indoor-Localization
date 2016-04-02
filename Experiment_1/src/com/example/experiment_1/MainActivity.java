package com.example.experiment_1;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button mNextButton;
	private Button mConfirmButton;
	private Button mstartButton;

	protected int loc[][]=new int[100][3];
	private String[][] check_result=new String[50][50];

	protected int numberOfTest=0;
	protected int numberOfAP=0;
	protected int numberOfCheck=0;

	// ����WifiManager����
	private WifiManager mWifiManager;
	// ɨ��������������б�
	private List<ScanResult> results;
	// ���������б�
	protected TextView msg1;
	protected TextView msg2;
	protected TextView msg3;

	protected String otherwifi="���������\n";
	protected String showwifi="wifi��ϢΪ��\n";
	protected String showloc="λ����ϢΪ��\n";

	TextView tvShow;
	private Timer mTimer = null;
	private TimerTask mTimerTask = null;

	private boolean isStop = true;

	//for DB
	Context myContext;
	DBHelper SQLDB;







	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*************** DB ***************/
		myContext=getApplicationContext();
		SQLDB=new DBHelper(myContext);
		Log.i("test_SQL", "succeed in SQL!");

		msg1 = (TextView) findViewById(R.id.textView1);
		msg2 = (TextView) findViewById(R.id.textView2);
		msg3 = (TextView) findViewById(R.id.textView3);







		/*************** start ****************/

		mstartButton=(Button)findViewById(R.id.start_button);
		mstartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				EditText locX=(EditText) findViewById(R.id.loc_x);//��ȡ�ı�
				EditText locY=(EditText) findViewById(R.id.loc_y);//��ȡ�ı�
				EditText locZ=(EditText) findViewById(R.id.loc_z);//��ȡ�ı�

				loc[numberOfTest][0]=Integer.parseInt(locX.getText().toString());
				loc[numberOfTest][1]=Integer.parseInt(locY.getText().toString());
				loc[numberOfTest][2]=Integer.parseInt(locZ.getText().toString());

				for(int i=0;i<50;i++){
					for(int j=0;j<50;j++){
						check_result[i][j]="0";
						check_result[i][j]="0";
					}
				}
				if("".equals(locX.getText().toString())||"".equals(locY.getText().toString())||"".equals(locZ.getText().toString())){
					Toast.makeText(MainActivity.this, R.string.please_enter_loc_toast, Toast.LENGTH_SHORT).show();
				}
				else if(isStop){
					Toast.makeText(MainActivity.this, R.string.start_toast, Toast.LENGTH_SHORT).show();
					tvShow = (TextView)findViewById(R.id.tvshow);
					tvShow.setText("");
					msg1.setText("");
					msg2.setText("");
					msg3.setText("");

					if (isStop) {
						startTimer();
					}else {
						stopTimer();
					}
					//timer.schedule(task, 1000, 2000); // 1s��ִ��task,����1s�ٴ�ִ��
				}
				else{
					Toast.makeText(MainActivity.this, R.string.already_start_toast, Toast.LENGTH_SHORT).show();
				}
			}
		});

		/**************** next ******************/

		mNextButton=(Button)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				//����������ݣ����ݿ�洢+1

				EditText locX=(EditText) findViewById(R.id.loc_x);//��ȡ�ı�
				EditText locY=(EditText) findViewById(R.id.loc_y);//��ȡ�ı�
				EditText locZ=(EditText) findViewById(R.id.loc_z);//��ȡ�ı�

				if("".equals(locX.getText().toString())||"".equals(locY.getText().toString())||"".equals(locZ.getText().toString())){
					Toast.makeText(MainActivity.this, R.string.please_enter_loc_toast, Toast.LENGTH_SHORT).show();
				}
				else{
					/*loc[numberOfTest][0]=Integer.parseInt(locX.getText().toString());
					loc[numberOfTest][1]=Integer.parseInt(locY.getText().toString());
					loc[numberOfTest][2]=Integer.parseInt(locZ.getText().toString());*/


					////////////DB////////////
					for(int i=0;i<numberOfAP;i++){
						if(!check_result[i][0].equals("0")){
							Log.i("check_result",check_result[i][0]+check_result[i][1]);

							//SQLDB.insert(loc[numberOfTest][0],loc[numberOfTest][1],loc[numberOfTest][2],
							//	check_result[i][0], check_result[i][1]);
							//check_result[i][0], );

						}else
							break;
					}
					/////////////////////////

					numberOfTest++;//�ڼ�����
					numberOfAP=0;
					locX.setText("");
					locY.setText("");
					locZ.setText("");
					tvShow.setText("");
					msg1.setText("");
					msg2.setText("");
					msg3.setText("");

					Toast.makeText(MainActivity.this, R.string.next_toast, Toast.LENGTH_SHORT).show();
				}
			}
		});





		/******************* confirm *********************/

		mConfirmButton=(Button)findViewById(R.id.confirm_button);
		mConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				EditText locX=(EditText) findViewById(R.id.loc_x);//��ȡ�ı�
				EditText locY=(EditText) findViewById(R.id.loc_y);//��ȡ�ı�
				EditText locZ=(EditText) findViewById(R.id.loc_z);//��ȡ�ı�

				if("".equals(locX.getText().toString())||"".equals(locY.getText().toString())||"".equals(locZ.getText().toString())){
					/*if(falseInteger.parseInt(APStrength[0][0][1])==0){
						Toast.makeText(MainActivity.this, R.string.no_data_toast, Toast.LENGTH_SHORT).show();
					}
					else{*/
					Toast.makeText(MainActivity.this, R.string.confirm_toast, Toast.LENGTH_SHORT).show();
					//Intent i=new Intent(MainActivity.this,ShowActivity.class);
					//startActivity(i);
					System.out.println("confirm!");
					printwifi();
					//}
				}
				else{
					Toast.makeText(MainActivity.this, R.string.please_enter_next_toast, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}



	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



	/************* checkWifi ****************/

	public void checkWiFi() {

		mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		mWifiManager.startScan();
		results = mWifiManager.getScanResults();
		showwifi="";
		int apnum=0;
		for (ScanResult result : results) {
			String BSSID=result.BSSID;
			int level=result.level;
			boolean ifcheck=false;

			for(int i=0;i<numberOfAP;i++){
				if(BSSID.equals(check_result[i][0])){
					check_result[i][numberOfCheck+1]=String.valueOf(level);
					ifcheck=true;
					showwifi+=numberOfCheck+":\n";
					showwifi+=check_result[i][0]+"��ǿ��Ϊ��"+check_result[i][numberOfCheck+1]+"\n";
					break;
				}
			}

			if(ifcheck==false){
				check_result[numberOfAP][0]=BSSID;
				check_result[numberOfAP][numberOfCheck+1]=String.valueOf(level);
				showwifi+=numberOfCheck+":\n";
				showwifi+=check_result[numberOfAP][0]+"��ǿ��Ϊ��"+check_result[numberOfAP][numberOfCheck+1]+"\n";
				numberOfAP++;//֮ǰû���ѵ���������WiFi
			}

			apnum++;

		}
		showwifi+="���ι���⵽:"+apnum+"��AP��";
		msg2.setText(showwifi);

		numberOfCheck++;
	}


	/************** printWiFi ***************/

	public void printwifi(){
		/*
		otherwifi="���������\n";
		for(int i=0;i<50;i++){
			otherwifi+="��" + (i+1)+ "��\tλ�ã�(" +loc[i][0]+ "," +loc[i][1]+ "," +loc[i][2]+ ",)\n";
			otherwifi+="AP�źţ�\n";
			if(Integer.parseInt(check_result[i][1])<0){
				otherwifi+=check_result[i][0]+"\t"+check_result[i][1]+"\n";
			}
			else break;
		}
		msg1.setText(otherwifi);
		 */
		tvShow.setText("");
		msg1.setText("");
		msg2.setText("");
		msg3.setText("");
		msg1.setText(SQLDB.get_all());
	}







	/*************** Timer *****************/

	private void stopTimer(){
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}	
	}
	private void startTimer(){
		if (mTimer == null) {
			mTimer = new Timer();
		}
		if (mTimerTask == null) {
			mTimerTask = new TimerTask() {
				@Override
				public void run() {
					// ��Ҫ������:������Ϣ
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				}
			};
		}

		if(mTimer != null && mTimerTask != null )
			mTimer.schedule(mTimerTask, 1000, 1000);
	}

	Handler handler = new Handler() {
		@SuppressLint("HandlerLeak")
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				boolean ifover=false;
				while(numberOfCheck>20){
					stopTimer();
					isStop=true;
					ifover=true;
					String show1="";
					for(int i=0;i<numberOfAP;i++){
						for(int j=0;j<numberOfCheck;j++){
							show1+=check_result[i][0]+"/"+check_result[i][j]+'\n';
						}
						check_result[i][1]=cal_str_average(check_result[i],numberOfCheck-1);
						Log.i("check_result","later:"+check_result[i][1]);

						if(check_result[i][0].equals("24:69:68:a4:4c:4e")){
							SQLDB.insert(loc[numberOfTest][0],loc[numberOfTest][1],loc[numberOfTest][2],
									check_result[i][0], check_result[i][1]);
						}

						//msg1.setText(show1);
						tvShow.setText("");
						msg1.setText(SQLDB.get_all());
						msg2.setText("");msg3.setText("");
					}
					numberOfCheck=0;
					Toast.makeText(MainActivity.this, R.string.complete_toast, Toast.LENGTH_SHORT).show();
				}
				if(ifover==false){
					tvShow.setText("clock:"+numberOfCheck);
					checkWiFi();
				}
			}
			super.handleMessage(msg);
		};
	};






	/************** cal_average ****************/
	/*
	void cal_str_average(String[]a,int number){
		int []temp=new int[number];
		int real_number=number;
		int average=0;
		int total=0;
		for(int i=0;i<number;i++){
			temp[i]=Integer.parseInt(a[i+1]);
			average+=temp[i];
			if(temp[i]==0)
				real_number--;
		}
		total=average;
		average=average/real_number;
		int fangcha=0;
		for(int i=0;i<number;i++)
			if(temp[i]!=0)
				fangcha+=((temp[i]-average)*(temp[i]-average));
		fangcha=fangcha/real_number;	
		while(fangcha>10)
		{
			int pre_average=average;
			int min_i=0;int max_i=0;//��ʶ�����Сֵ
			for(int i=0;i<number;i++)
				if(temp[i]!=0)
				{
					max_i=i;min_i=i;break;
				}
			for(int i=0;i<number;i++){
				if(temp[i]!=0){
					if(temp[i]>temp[max_i])
						max_i=i;
					if(temp[i]<temp[min_i])
						min_i=i;
				}
			}
			//System.out.println(max_i+" "+min_i);
			if(max_i==min_i)
			{
				total=total-temp[max_i];
				real_number--;
			}
			else if(max_i!=min_i&&real_number!=2){
				total=total-temp[max_i]-temp[min_i];
				real_number-=2;
			}//real_number=2ʱ ��-2�͵���0��  �������������ķ���
			average=total/real_number;
			temp[min_i]=0;temp[max_i]=0;
			fangcha=0;
			for(int i=0;i<number;i++)
				if(temp[i]!=0)
					fangcha+=((temp[i]-average)*(temp[i]-average));
			fangcha=fangcha/real_number;
			if(fangcha==0&&real_number==2)
				average=pre_average;
		}
		a[1]=String.valueOf((float)average);
	}
	 */

	public static String cal_str_average(String[]a,int number){
		double []temp=new double[number];
		int real_number=number;
		double average=0.0;//ƽ��ֵ
		double total=0;//��
		for(int i=0;i<number;i++){
			temp[i]=Double.parseDouble(a[i+1]);
			total+=temp[i];
			if(temp[i]==0)
				real_number--;
		}
		average=(double)total/(double)real_number;
		double variance=0.0;//����
		for(int i=0;i<number;i++)
			if(temp[i]!=0)
				variance+=((temp[i]-average)*(temp[i]-average));
		variance=variance/real_number;	
		while(variance>25.0){//�������ʱ��ɾ��ƫ����������
			double pre_average=average;
			int min_i=0;int max_i=0;//��ʶ�����Сֵ
			for(int i=0;i<number;i++)
				if(temp[i]!=0){
					max_i=i;min_i=i;break;
				}
			for(int i=0;i<number;i++){
				if(temp[i]!=0){
					if(temp[i]>temp[max_i])
						max_i=i;
					else if(temp[i]<temp[min_i])
						min_i=i;
				}
			}
			//System.out.println(max_i+" "+min_i);
			if(max_i==min_i){
				total=total-temp[max_i];
				real_number--;
			}
			else if(real_number!=2){
				total=total-temp[max_i]-temp[min_i];
				real_number-=2;
			}//real_number=2ʱ ��-2�͵���0��  �������������ķ���
			average=(double)total/(double)real_number;
			temp[min_i]=0;temp[max_i]=0;
			variance=0.0;
			for(int i=0;i<number;i++)
				if(temp[i]!=0)
					variance+=((temp[i]-average)*(temp[i]-average));
			variance=variance/real_number;
			if(variance==0.0&&real_number==2)
				average=pre_average;
		}
		Log.i("check_result","num:"+average+"////?"+String.valueOf(average));
		return String.valueOf(average);
	}
}



