package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class imagedownloader extends android.app.Service {
	public static class imagedownloader_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, imagedownloader.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static imagedownloader mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return imagedownloader.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.imagedownloader");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.imagedownloader", processBA, _service);
		}
        BA.LogInfo("** Service (imagedownloader) Create **");
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
    	BA.LogInfo("** Service (imagedownloader) Start **");
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
        BA.LogInfo("** Service (imagedownloader) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = null;
public static anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;
public static String  _activityispaused() throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub ActivityIsPaused";
 //BA.debugLineNum = 64;BA.debugLine="tasks.Clear";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.Clear();
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _download(anywheresoftware.b4a.objects.collections.Map _imageviewsmap) throws Exception{
int _i = 0;
String _link = "";
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
 //BA.debugLineNum = 25;BA.debugLine="Sub Download (ImageViewsMap As Map)";
 //BA.debugLineNum = 26;BA.debugLine="For i = 0 To ImageViewsMap.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (_imageviewsmap.getSize()-1);
for (_i = (int) (0); (step15 > 0 && _i <= limit15) || (step15 < 0 && _i >= limit15); _i = ((int)(0 + _i + step15))) {
 //BA.debugLineNum = 27;BA.debugLine="tasks.Put(ImageViewsMap.GetKeyAt(i), ImageViewsM";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.Put(_imageviewsmap.GetKeyAt(_i),_imageviewsmap.GetValueAt(_i));
 //BA.debugLineNum = 28;BA.debugLine="Dim link As String = ImageViewsMap.GetValueAt(i)";
_link = BA.ObjectToString(_imageviewsmap.GetValueAt(_i));
 //BA.debugLineNum = 29;BA.debugLine="If cache.ContainsKey(link) Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.ContainsKey((Object)(_link))) { 
 //BA.debugLineNum = 30;BA.debugLine="Dim iv As ImageView = ImageViewsMap.GetKeyAt(i)";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv.setObject((android.widget.ImageView)(_imageviewsmap.GetKeyAt(_i)));
 //BA.debugLineNum = 31;BA.debugLine="iv.SetBackgroundImage(cache.Get(link))";
_iv.SetBackgroundImage((android.graphics.Bitmap)(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Get((Object)(_link))));
 }else if(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.ContainsKey((Object)(_link))==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 33;BA.debugLine="ongoingTasks.Put(link, \"\")";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.Put((Object)(_link),(Object)(""));
 //BA.debugLineNum = 34;BA.debugLine="Dim j As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 35;BA.debugLine="j.Initialize(link, Me)";
_j._initialize(processBA,_link,imagedownloader.getObject());
 //BA.debugLineNum = 36;BA.debugLine="j.Download(link)";
_j._download(_link);
 //BA.debugLineNum = 37;BA.debugLine="j.GetRequest.SetHeader(\"Cache-Control\", \"no-cac";
_j._getrequest().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 38;BA.debugLine="j.GetRequest.Timeout = DateTime.TicksPerSecond";
_j._getrequest().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 };
 }
};
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
int _i = 0;
String _link = "";
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
 //BA.debugLineNum = 43;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 44;BA.debugLine="ongoingTasks.Remove(Job.JobName)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.Remove((Object)(_job._jobname));
 //BA.debugLineNum = 45;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 46;BA.debugLine="Dim bmp As Bitmap = Job.GetBitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = _job._getbitmap();
 //BA.debugLineNum = 47;BA.debugLine="cache.Put(Job.JobName, bmp)";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Put((Object)(_job._jobname),(Object)(_bmp.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="If tasks.IsInitialized Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.IsInitialized()) { 
 //BA.debugLineNum = 49;BA.debugLine="For i = 0 To tasks.Size - 1";
{
final int step37 = 1;
final int limit37 = (int) (_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.getSize()-1);
for (_i = (int) (0); (step37 > 0 && _i <= limit37) || (step37 < 0 && _i >= limit37); _i = ((int)(0 + _i + step37))) {
 //BA.debugLineNum = 50;BA.debugLine="Dim link As String = tasks.GetValueAt(i)";
_link = BA.ObjectToString(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.GetValueAt(_i));
 //BA.debugLineNum = 51;BA.debugLine="If link = Job.JobName Then";
if ((_link).equals(_job._jobname)) { 
 //BA.debugLineNum = 52;BA.debugLine="Dim iv As ImageView = tasks.GetKeyAt(i)";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
_iv.setObject((android.widget.ImageView)(_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.GetKeyAt(_i)));
 //BA.debugLineNum = 53;BA.debugLine="iv.SetBackgroundImage(bmp)";
_iv.SetBackgroundImage((android.graphics.Bitmap)(_bmp.getObject()));
 };
 }
};
 };
 }else {
 //BA.debugLineNum = 58;BA.debugLine="Log(\"Error downloading image: \" & Job.JobName &";
anywheresoftware.b4a.keywords.Common.Log("Error downloading image: "+_job._jobname+anywheresoftware.b4a.keywords.Common.CRLF+_job._errormessage);
 };
 //BA.debugLineNum = 60;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private cache As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 7;BA.debugLine="Private tasks As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 8;BA.debugLine="Private ongoingTasks As Map";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 12;BA.debugLine="tasks.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv5.Initialize();
 //BA.debugLineNum = 13;BA.debugLine="cache.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv6.Initialize();
 //BA.debugLineNum = 14;BA.debugLine="ongoingTasks.Initialize";
_vvvvvvvvvvvvvvvvvvvvvvvvvvvvv7.Initialize();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
}
