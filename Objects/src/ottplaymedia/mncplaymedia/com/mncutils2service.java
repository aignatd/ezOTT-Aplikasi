package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class mncutils2service extends android.app.Service {
	public static class mncutils2service_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, mncutils2service.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static mncutils2service mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return mncutils2service.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.mncutils2service");
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        processBA.setActivityPaused(false);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.mncutils2service", processBA, _service);
		}
        BA.LogInfo("** Service (mncutils2service) Create **");
        processBA.raiseEvent(null, "service_create");
        processBA.runHook("oncreate", this, null);
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		handleStart(intent);
    }
    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
    	handleStart(intent);
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (mncutils2service) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
        BA.LogInfo("** Service (mncutils2service) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.http.HttpClientWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = null;
public static String _vvvvvvvvvvvv4 = "";
public static int _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = 0;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public static String  _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4(int _taskid,boolean _success,String _errormessage) throws Exception{
ottplaymedia.mncplaymedia.com.mnchttpjob _job = null;
 //BA.debugLineNum = 110;BA.debugLine="Sub CompleteJob(TaskId As Int, success As Boolean,";
 //BA.debugLineNum = 111;BA.debugLine="Dim job As MNCHttpJob";
_job = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 113;BA.debugLine="Try";
try { //BA.debugLineNum = 114;BA.debugLine="job = TaskIdToJob.Get(TaskId)";
_job = (ottplaymedia.mncplaymedia.com.mnchttpjob)(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.Get((Object)(_taskid)));
 //BA.debugLineNum = 115;BA.debugLine="TaskIdToJob.Remove(TaskId)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.Remove((Object)(_taskid));
 //BA.debugLineNum = 116;BA.debugLine="job.success = success";
_job._vvvvvvvvvvvvvvvvvvvvvv2 = _success;
 //BA.debugLineNum = 117;BA.debugLine="job.errorMessage = errorMessage";
_job._vvvvvvvvvvvvvvvvvvvvvv5 = _errormessage;
 //BA.debugLineNum = 118;BA.debugLine="job.Complete(TaskId)";
_job._vvvvvvvvvvvv6(_taskid);
 //BA.debugLineNum = 119;BA.debugLine="job.ResMap = tmpMap";
_job._vvvvvvvvvvvvvvvvvvvvvvv2 = _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6;
 } 
       catch (Exception e91) {
			processBA.setLastException(e91); //BA.debugLineNum = 121;BA.debugLine="Log(errorMessage)";
anywheresoftware.b4a.keywords.Common.Log(_errormessage);
 };
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _hc_responseerror(anywheresoftware.b4a.http.HttpClientWrapper.HttpResponeWrapper _response,String _reason,int _statuscode,int _taskid) throws Exception{
int _i = 0;
int _k = 0;
String[] _z = null;
String[] _z1 = null;
anywheresoftware.b4a.objects.collections.List _lstcookie = null;
 //BA.debugLineNum = 73;BA.debugLine="Sub hc_ResponseError (Response As HttpResponse, Re";
 //BA.debugLineNum = 74;BA.debugLine="Dim i 		As Int = 0";
_i = (int) (0);
 //BA.debugLineNum = 75;BA.debugLine="Dim k 		As Int = 0";
_k = (int) (0);
 //BA.debugLineNum = 76;BA.debugLine="Dim z() 	As String";
_z = new String[(int) (0)];
java.util.Arrays.fill(_z,"");
 //BA.debugLineNum = 77;BA.debugLine="Dim z1() 	As String";
_z1 = new String[(int) (0)];
java.util.Arrays.fill(_z1,"");
 //BA.debugLineNum = 78;BA.debugLine="Dim lstCookie As List";
_lstcookie = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 80;BA.debugLine="If Response <> Null Then";
if (_response!= null) { 
 //BA.debugLineNum = 81;BA.debugLine="tmpMap.Clear";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Clear();
 //BA.debugLineNum = 82;BA.debugLine="tmpMap = Response.GetHeaders";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = _response.GetHeaders();
 //BA.debugLineNum = 83;BA.debugLine="tmpMap.Put(\"StatusCode\", StatusCode)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Put((Object)("StatusCode"),(Object)(_statuscode));
 //BA.debugLineNum = 85;BA.debugLine="If tmpMap.ContainsKey(\"Set-Cookie\") Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.ContainsKey((Object)("Set-Cookie"))) { 
 //BA.debugLineNum = 86;BA.debugLine="lstCookie = tmpMap.Get(\"Set-Cookie\")";
_lstcookie.setObject((java.util.List)(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Get((Object)("Set-Cookie"))));
 //BA.debugLineNum = 87;BA.debugLine="z = Regex.Split(\";\", lstCookie.Get(0))";
_z = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_lstcookie.Get((int) (0))));
 //BA.debugLineNum = 88;BA.debugLine="z1 = Regex.Split(\"=\", z(0))";
_z1 = anywheresoftware.b4a.keywords.Common.Regex.Split("=",_z[(int) (0)]);
 //BA.debugLineNum = 89;BA.debugLine="tmpMap.Put(\"JSESSIONID\", z1(1))";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Put((Object)("JSESSIONID"),(Object)(_z1[(int) (1)]));
 };
 //BA.debugLineNum = 92;BA.debugLine="Try";
try { //BA.debugLineNum = 93;BA.debugLine="Log(Response.GetString(\"UTF8\"))";
anywheresoftware.b4a.keywords.Common.Log(_response.GetString("UTF8"));
 } 
       catch (Exception e69) {
			processBA.setLastException(e69); //BA.debugLineNum = 95;BA.debugLine="Log(\"Failed to read error message.\")";
anywheresoftware.b4a.keywords.Common.Log("Failed to read error message.");
 };
 //BA.debugLineNum = 98;BA.debugLine="If Floor(StatusCode / 100) = 3 Then";
if (anywheresoftware.b4a.keywords.Common.Floor(_statuscode/(double)100)==3) { 
 //BA.debugLineNum = 99;BA.debugLine="i = 1";
_i = (int) (1);
 };
 };
 //BA.debugLineNum = 103;BA.debugLine="If i = 0 Then";
if (_i==0) { 
 //BA.debugLineNum = 104;BA.debugLine="CompleteJob(TaskId, False, Reason)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4(_taskid,anywheresoftware.b4a.keywords.Common.False,_reason);
 }else {
 //BA.debugLineNum = 106;BA.debugLine="CompleteJob(TaskId, True, \"\")";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4(_taskid,anywheresoftware.b4a.keywords.Common.True,"");
 };
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
public static String  _hc_responsesuccess(anywheresoftware.b4a.http.HttpClientWrapper.HttpResponeWrapper _response,int _taskid) throws Exception{
String[] _z = null;
String[] _z1 = null;
anywheresoftware.b4a.objects.collections.List _lstcookie = null;
 //BA.debugLineNum = 46;BA.debugLine="Sub hc_ResponseSuccess (Response As HttpResponse,";
 //BA.debugLineNum = 47;BA.debugLine="Dim z() 	As String";
_z = new String[(int) (0)];
java.util.Arrays.fill(_z,"");
 //BA.debugLineNum = 48;BA.debugLine="Dim z1() 	As String";
_z1 = new String[(int) (0)];
java.util.Arrays.fill(_z1,"");
 //BA.debugLineNum = 49;BA.debugLine="Dim lstCookie As List";
_lstcookie = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 51;BA.debugLine="tmpMap.Clear";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Clear();
 //BA.debugLineNum = 52;BA.debugLine="tmpMap = Response.GetHeaders";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = _response.GetHeaders();
 //BA.debugLineNum = 54;BA.debugLine="If tmpMap.ContainsKey(\"Set-Cookie\") Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.ContainsKey((Object)("Set-Cookie"))) { 
 //BA.debugLineNum = 55;BA.debugLine="lstCookie = tmpMap.Get(\"Set-Cookie\")";
_lstcookie.setObject((java.util.List)(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Get((Object)("Set-Cookie"))));
 //BA.debugLineNum = 56;BA.debugLine="z = Regex.Split(\";\", lstCookie.Get(0))";
_z = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_lstcookie.Get((int) (0))));
 //BA.debugLineNum = 57;BA.debugLine="z1 = Regex.Split(\"=\", z(0))";
_z1 = anywheresoftware.b4a.keywords.Common.Regex.Split("=",_z[(int) (0)]);
 //BA.debugLineNum = 58;BA.debugLine="tmpMap.Put(\"JSESSIONID\", z1(1))";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Put((Object)("JSESSIONID"),(Object)(_z1[(int) (1)]));
 };
 //BA.debugLineNum = 61;BA.debugLine="If File.Exists(TempFolder, TaskId) Then File.Dele";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_vvvvvvvvvvvv4,BA.NumberToString(_taskid))) { 
anywheresoftware.b4a.keywords.Common.File.Delete(_vvvvvvvvvvvv4,BA.NumberToString(_taskid));};
 //BA.debugLineNum = 62;BA.debugLine="Response.GetAsynchronously(\"response\", File.OpenO";
_response.GetAsynchronously(processBA,"response",(java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(_vvvvvvvvvvvv4,BA.NumberToString(_taskid),anywheresoftware.b4a.keywords.Common.False).getObject()),anywheresoftware.b4a.keywords.Common.True,_taskid);
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private hc 					As HttpClient";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.http.HttpClientWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private TaskIdToJob As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 10;BA.debugLine="Public TempFolder 	As String";
_vvvvvvvvvvvv4 = "";
 //BA.debugLineNum = 11;BA.debugLine="Private taskCounter As Int";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = 0;
 //BA.debugLineNum = 12;BA.debugLine="Private tmpMap 			As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _response_streamfinish(boolean _success,int _taskid) throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Sub Response_StreamFinish (Success As Boolean, Tas";
 //BA.debugLineNum = 66;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 67;BA.debugLine="CompleteJob(TaskId, Success, \"\")";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4(_taskid,_success,"");
 }else {
 //BA.debugLineNum = 69;BA.debugLine="CompleteJob(TaskId, Success, LastException.Messa";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv4(_taskid,_success,anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage());
 };
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 16;BA.debugLine="TempFolder = File.DirInternalCache";
_vvvvvvvvvvvv4 = anywheresoftware.b4a.keywords.Common.File.getDirInternalCache();
 //BA.debugLineNum = 17;BA.debugLine="If hc.IsInitialized = False Then hc.Initialize(\"h";
if (_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.Initialize("hc");};
 //BA.debugLineNum = 19;BA.debugLine="TaskIdToJob.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.Initialize();
 //BA.debugLineNum = 20;BA.debugLine="tmpMap.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Initialize();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static int  _submitjob(ottplaymedia.mncplaymedia.com.mnchttpjob _job) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Public Sub SubmitJob(job As MNCHttpJob) As Int";
 //BA.debugLineNum = 32;BA.debugLine="taskCounter = taskCounter + 1";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = (int) (_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0+1);
 //BA.debugLineNum = 33;BA.debugLine="TaskIdToJob.Put(taskCounter, job)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.Put((Object)(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0),(Object)(_job));
 //BA.debugLineNum = 35;BA.debugLine="If job.Username <> \"\" And job.Password <> \"\" Then";
if ((_job._vvvvvvvvvvvvvvvvvvvvvv3).equals("") == false && (_job._vvvvvvvvvvvvvvvvvvvvvv4).equals("") == false) { 
 //BA.debugLineNum = 36;BA.debugLine="hc.ExecuteCredentials(job.GetRequest, taskCounte";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.ExecuteCredentials(processBA,_job._vvvvvvvvvvvvvvvvvvvvv1(),_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0,_job._vvvvvvvvvvvvvvvvvvvvvv3,_job._vvvvvvvvvvvvvvvvvvvvvv4);
 }else {
 //BA.debugLineNum = 38;BA.debugLine="hc.Execute(job.GetRequest, taskCounter)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.Execute(processBA,_job._vvvvvvvvvvvvvvvvvvvvv1(),_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0);
 };
 //BA.debugLineNum = 41;BA.debugLine="hc.SetHttpParameter(\"http.protocol.handle-redirec";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.SetHttpParameter("http.protocol.handle-redirects",(Object)(anywheresoftware.b4a.keywords.Common.False));
 //BA.debugLineNum = 43;BA.debugLine="Return taskCounter";
if (true) return _vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv0;
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return 0;
}
}
