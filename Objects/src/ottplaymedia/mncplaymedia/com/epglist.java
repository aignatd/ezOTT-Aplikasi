package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class epglist extends Activity implements B4AActivity{
	public static epglist mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.epglist");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (epglist).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.epglist");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.epglist", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (epglist) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (epglist) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return epglist.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (epglist) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (epglist) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _vvvvvvvvvv5 = "";
public static String _vvvvvvvvvv6 = "";
public static anywheresoftware.b4a.objects.collections.List _vvvvvvvvvv7 = null;
public static anywheresoftware.b4a.objects.collections.List _vvvvvvvvvv0 = null;
public static anywheresoftware.b4a.objects.collections.List _vvvvvvvvvvv1 = null;
public static Object _vvvvvvvvvvv2 = null;
public static int _vvvvvvvvvvv3 = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivchaname = null;
public flm.b4a.betterdialogs.BetterDialogs _vvvvvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public com.datasteam.b4a.xtraviews.MovieViewControl _gifepg = null;
public anywheresoftware.b4a.objects.ListViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvv5 = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _vvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivbackepg = null;
public anywheresoftware.b4a.objects.IntentWrapper _vvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public static int _vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
public anywheresoftware.b4a.phone.PackageManagerWrapper _vvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.PanelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivepgdet = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblepgdet = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _svepg = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.register _vvvvvvvvvvvvvvvv3 = null;
public ottplaymedia.mncplaymedia.com.pilihan _vvvvvvvvvvvvvvvv4 = null;
public ottplaymedia.mncplaymedia.com.epgdownloader _vvvvvvvvvvvvvvvv5 = null;
public ottplaymedia.mncplaymedia.com.imagedownloader _vvvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.content _vvvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.webview _vvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.collections.List _paclist = null;
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 49;BA.debugLine="Activity.LoadLayout(\"EPGList\")";
mostCurrent._activity.LoadLayout("EPGList",mostCurrent.activityBA);
 //BA.debugLineNum = 51;BA.debugLine="GifEPG.Load(File.DirAssets, \"spin.gif\", True)";
mostCurrent._gifepg.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"spin.gif",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 52;BA.debugLine="GifEPG.Visible = True";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 54;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 55;BA.debugLine="Try";
try { //BA.debugLineNum = 56;BA.debugLine="Fungsi.SetDirProg(\"\")";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv1(mostCurrent.activityBA,"");
 //BA.debugLineNum = 57;BA.debugLine="Fungsi.BacaFileConfig";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv1(mostCurrent.activityBA);
 //BA.debugLineNum = 58;BA.debugLine="Fungsi.mpList.Put(\"DirDBS\", Fungsi.DirDBS)";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("DirDBS"),(Object)(mostCurrent._vvvvvvvvvvvvvvv0._v6));
 } 
       catch (Exception e36) {
			processBA.setLastException(e36); //BA.debugLineNum = 60;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.InitFa";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 };
 //BA.debugLineNum = 66;BA.debugLine="If File.Exists(File.DirAssets, \"config.json\") The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json")) { 
 //BA.debugLineNum = 67;BA.debugLine="If Fungsi.CacahJSON(File.ReadString(File.DirAsse";
if (mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv2(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json"),"NextArray")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 68;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.InitFa";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 69;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 }else {
 //BA.debugLineNum = 72;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.LoadFai";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 73;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 77;BA.debugLine="Dim pacList As List = pm.GetInstalledPackages";
_paclist = new anywheresoftware.b4a.objects.collections.List();
_paclist = mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6.GetInstalledPackages();
 //BA.debugLineNum = 78;BA.debugLine="ipack = pacList.IndexOf(\"com.nexstreaming.app.nex";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = _paclist.IndexOf((Object)("com.nexstreaming.app.nexplayersample"));
 //BA.debugLineNum = 80;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 81;BA.debugLine="ups.Initialize(ups.ACTION_VIEW, \"file://\" & File";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_VIEW,"file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._vvvvvvvvvvvvvvv0._v6,"nexplayersdklitevm.apk"));
 //BA.debugLineNum = 82;BA.debugLine="ups.SetType(\"application/vnd.android.package-arc";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.SetType("application/vnd.android.package-archive");
 //BA.debugLineNum = 83;BA.debugLine="StartActivity(ups)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));
 };
 //BA.debugLineNum = 86;BA.debugLine="bmpTemp.Initialize(BmpPre)";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.Initialize((android.graphics.Bitmap)(_vvvvvvvvvvv2));
 //BA.debugLineNum = 87;BA.debugLine="bmpTemp.Gravity = Gravity.CENTER";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 89;BA.debugLine="ivChaName.Bitmap = bmpTemp.Bitmap";
mostCurrent._ivchaname.setBitmap(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6.getBitmap());
 //BA.debugLineNum = 90;BA.debugLine="ivChaName.Gravity = Gravity.FILL";
mostCurrent._ivchaname.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 92;BA.debugLine="bmpImage.Initialize(\"\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 93;BA.debugLine="bmpImage.Background = Fungsi.GetDrawable(\"icoplay";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv4.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"icoplay")));
 //BA.debugLineNum = 94;BA.debugLine="bmpImage.Gravity = Gravity.FILL";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 97;BA.debugLine="ivBackEPG.Background = Fungsi.GetDrawable(\"icobac";
mostCurrent._ivbackepg.setBackground((android.graphics.drawable.Drawable)(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"icoback")));
 //BA.debugLineNum = 98;BA.debugLine="init_listEPG";
_init_listepg();
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 155;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 156;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 157;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 158;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 159;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 161;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 162;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 163;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 164;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 165;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 166;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 168;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 171;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 151;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 152;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 102;BA.debugLine="Fungsi.BacaFileConfig";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv1(mostCurrent.activityBA);
 //BA.debugLineNum = 103;BA.debugLine="Fungsi.DirDBS = Fungsi.mpList.Get(\"DirDBS\")";
mostCurrent._vvvvvvvvvvvvvvv0._v6 = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("DirDBS")));
 //BA.debugLineNum = 105;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 106;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoPlaye";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv0,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 108;BA.debugLine="ups.Initialize(ups.ACTION_MAIN, \"\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.Initialize(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.ACTION_MAIN,"");
 //BA.debugLineNum = 109;BA.debugLine="ups = pm.GetApplicationIntent(\"com.nexstreaming.";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4 = mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6.GetApplicationIntent("com.nexstreaming.app.nexplayersample");
 //BA.debugLineNum = 110;BA.debugLine="ups.PutExtra(\"Company\", \"PT-Infokom\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("Company",(Object)("PT-Infokom"));
 //BA.debugLineNum = 111;BA.debugLine="ups.PutExtra(\"VCASAddr\", \"202.80.222.36:80\")";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("VCASAddr",(Object)("202.80.222.36:80"));
 };
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private ivChaName 		As ImageView";
mostCurrent._ivchaname = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private BDEPGList		As BetterDialogs";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3 = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 25;BA.debugLine="Private GifEPG 			As MovieViewControl";
mostCurrent._gifepg = new com.datasteam.b4a.xtraviews.MovieViewControl();
 //BA.debugLineNum = 26;BA.debugLine="Private lvEPG 			As ListView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv5 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private bmpTemp			As BitmapDrawable";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 28;BA.debugLine="Private bmpImage		As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private bmpFav			As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private ivBackEPG 		As ImageView";
mostCurrent._ivbackepg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private ups 				As Intent";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private ipack				As Int";
_vvvvvvvvvvvvvvvvvvvvvvvv5 = 0;
 //BA.debugLineNum = 35;BA.debugLine="Private pm 					As PackageManager";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private pEPGDet 		As Panel";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private ivEPGDet 		As ImageView";
mostCurrent._ivepgdet = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lblEPGDet 		As Label";
mostCurrent._lblepgdet = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private svEPG 			As ScrollView";
mostCurrent._svepg = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _init_listepg() throws Exception{
String _strcmd = "";
ottplaymedia.mncplaymedia.com.mnchttpjob _chnjob = null;
 //BA.debugLineNum = 115;BA.debugLine="Sub init_listEPG";
 //BA.debugLineNum = 116;BA.debugLine="Private strCMD 	As String";
_strcmd = "";
 //BA.debugLineNum = 117;BA.debugLine="Private ChnJob 	As MNCHttpJob";
_chnjob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 119;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 120;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 122;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 123;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 124;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 125;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 126;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 127;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="If Aktif = 1 Then";
if (_vvvvvvvvvvv3==1) { 
 //BA.debugLineNum = 130;BA.debugLine="strCMD = Fungsi.mpList.Get(\"URLLiveTVEPG\")";
_strcmd = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLLiveTVEPG")));
 }else {
 //BA.debugLineNum = 132;BA.debugLine="strCMD = Fungsi.mpList.Get(\"URLListEPG\")";
_strcmd = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLListEPG")));
 };
 //BA.debugLineNum = 135;BA.debugLine="strCMD = strCMD & Fungsi.mpList.Get(\"channelcode\"";
_strcmd = _strcmd+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("channelcode")))+_vvvvvvvvvv5+"&"+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("columncode")))+_vvvvvvvvvv6;
 //BA.debugLineNum = 136;BA.debugLine="ChnJob.Initialize(\"GetListEPG\", Me)";
_chnjob._initialize(processBA,"GetListEPG",epglist.getObject());
 //BA.debugLineNum = 138;BA.debugLine="Try";
try { //BA.debugLineNum = 139;BA.debugLine="ChnJob.Download(strCMD)";
_chnjob._download(_strcmd);
 //BA.debugLineNum = 140;BA.debugLine="ChnJob.GetRequest.SetHeader(\"Cache-Control\", \"no";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 141;BA.debugLine="ChnJob.GetRequest.Timeout = DateTime.TicksPerSec";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 //BA.debugLineNum = 142;BA.debugLine="ChnJob.Tag = strCMD";
_chnjob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)(_strcmd);
 } 
       catch (Exception e102) {
			processBA.setLastException(e102); //BA.debugLineNum = 144;BA.debugLine="ChnJob.Release";
_chnjob._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 145;BA.debugLine="Fungsi.mpList.Put(\"List EPG\", \"No List EPG\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("List EPG"),(Object)("No List EPG"));
 //BA.debugLineNum = 146;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoEPGLi";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv7,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return "";
}
public static String  _ivbackepg_click() throws Exception{
 //BA.debugLineNum = 368;BA.debugLine="Sub ivBackEPG_Click";
 //BA.debugLineNum = 369;BA.debugLine="Activity_KeyPress(4)";
_activity_keypress((int) (4));
 //BA.debugLineNum = 370;BA.debugLine="End Sub";
return "";
}
public static String  _ivepgdet_click() throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _ivtmp = null;
String _strcmd = "";
ottplaymedia.mncplaymedia.com.mnchttpjob _chnjob = null;
 //BA.debugLineNum = 336;BA.debugLine="Sub ivEPGDet_Click";
 //BA.debugLineNum = 337;BA.debugLine="Dim ivTmp		As ImageView = Sender";
_ivtmp = new anywheresoftware.b4a.objects.ImageViewWrapper();
_ivtmp.setObject((android.widget.ImageView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 338;BA.debugLine="Dim strCMD 	As String";
_strcmd = "";
 //BA.debugLineNum = 339;BA.debugLine="Dim ChnJob 	As MNCHttpJob";
_chnjob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 341;BA.debugLine="ivEPGDet.Visible = True";
mostCurrent._ivepgdet.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 342;BA.debugLine="CallSub(ImageDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 343;BA.debugLine="CallSub(EPGDownloader, \"ActivityIsPaused\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()),"ActivityIsPaused");
 //BA.debugLineNum = 345;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 346;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 347;BA.debugLine="StopService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 348;BA.debugLine="CancelScheduledService(ImageDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv6.getObject()));
 //BA.debugLineNum = 349;BA.debugLine="StopService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 350;BA.debugLine="CancelScheduledService(EPGDownloader)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvv5.getObject()));
 //BA.debugLineNum = 352;BA.debugLine="strCMD = Fungsi.mpList.Get(\"URLMovieEPG\") & Fungs";
_strcmd = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLMovieEPG")))+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("chancode")))+_vvvvvvvvvv5+"&"+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("pcode")))+BA.ObjectToString(_ivtmp.getTag());
 //BA.debugLineNum = 353;BA.debugLine="ChnJob.Initialize(\"GetPrevEPG\", Me)";
_chnjob._initialize(processBA,"GetPrevEPG",epglist.getObject());
 //BA.debugLineNum = 355;BA.debugLine="Try";
try { //BA.debugLineNum = 356;BA.debugLine="ChnJob.Download(strCMD)";
_chnjob._download(_strcmd);
 //BA.debugLineNum = 357;BA.debugLine="ChnJob.GetRequest.SetHeader(\"Cache-Control\", \"no";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 358;BA.debugLine="ChnJob.GetRequest.Timeout = DateTime.TicksPerSec";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 //BA.debugLineNum = 359;BA.debugLine="ChnJob.Tag = strCMD";
_chnjob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)(_strcmd);
 } 
       catch (Exception e272) {
			processBA.setLastException(e272); //BA.debugLineNum = 361;BA.debugLine="ChnJob.Release";
_chnjob._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 362;BA.debugLine="Fungsi.mpList.Put(\"Movie EPG\", \"No Movie EPG\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Movie EPG"),(Object)("No Movie EPG"));
 //BA.debugLineNum = 363;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoMovie";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv6,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 366;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(ottplaymedia.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
adr.stringfunctions.stringfunctions _sf = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.List _listepg = null;
anywheresoftware.b4a.objects.collections.List _listdesc = null;
anywheresoftware.b4a.objects.collections.List _lstbackup = null;
int _g = 0;
int _h = 0;
String[] _strbackup = null;
anywheresoftware.b4a.objects.PanelWrapper _panepg = null;
anywheresoftware.b4a.objects.collections.List _waktu = null;
String _tanggal = "";
 //BA.debugLineNum = 174;BA.debugLine="Sub JobDone(MNChttp As MNCHttpJob)";
 //BA.debugLineNum = 175;BA.debugLine="Dim sf				As StringFunctions";
_sf = new adr.stringfunctions.stringfunctions();
 //BA.debugLineNum = 176;BA.debugLine="Dim parser 		As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 177;BA.debugLine="Dim mresult		As Map";
_mresult = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 178;BA.debugLine="Dim i 				As Int";
_i = 0;
 //BA.debugLineNum = 179;BA.debugLine="Dim ListEPG		As List";
_listepg = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 180;BA.debugLine="Dim ListDesc	As List";
_listdesc = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 182;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 183;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 186;BA.debugLine="If MNChttp.Success = False Then";
if (_mnchttp._vvvvvvvvvvvvvvvvvvvvvv2==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 187;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out";
if ((_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"timed out")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"refused")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"FileNotFound")>0)) { 
 //BA.debugLineNum = 190;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 191;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoKone";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv6,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 193;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found";
if (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Not Found")<0) { 
 //BA.debugLineNum = 194;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 195;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.Unkno";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvv0,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 197;BA.debugLine="If MNChttp.JobName = \"Koneksi\" Then";
if ((_mnchttp._vvvvvvvvvvvvvvvvvvvvvv1).equals("Koneksi")) { 
 //BA.debugLineNum = 198;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 };
 };
 };
 //BA.debugLineNum = 204;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Offline"));
 //BA.debugLineNum = 205;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 }else {
 //BA.debugLineNum = 209;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Online\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Online"));
 //BA.debugLineNum = 211;BA.debugLine="Select MNChttp.JobName";
switch (BA.switchObjectToInt(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv1,"GetListEPG","GetPrevEPG")) {
case 0:
 //BA.debugLineNum = 214;BA.debugLine="Try";
try { //BA.debugLineNum = 215;BA.debugLine="parser.Initialize(MNChttp.GetString)";
_parser.Initialize(_mnchttp._vvvvvvvvvvvvvvvvvvvvv2());
 //BA.debugLineNum = 216;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 } 
       catch (Exception e159) {
			processBA.setLastException(e159); //BA.debugLineNum = 218;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 219;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.Fail";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv4,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 220;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 223;BA.debugLine="Try";
try { //BA.debugLineNum = 224;BA.debugLine="mresult.Initialize";
_mresult.Initialize();
 //BA.debugLineNum = 225;BA.debugLine="mresult = parser.NextObject";
_mresult = _parser.NextObject();
 } 
       catch (Exception e167) {
			processBA.setLastException(e167); //BA.debugLineNum = 227;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 228;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.Fail";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv5,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 //BA.debugLineNum = 229;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 232;BA.debugLine="If mresult.Get(\"returncode\") <> \"0\" Then";
if ((_mresult.Get((Object)("returncode"))).equals((Object)("0")) == false) { 
 //BA.debugLineNum = 233;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 234;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 235;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, mresult.Get";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,BA.ObjectToString(_mresult.Get((Object)("errormsg"))),mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 237;BA.debugLine="Dim lstBackup As List";
_lstbackup = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 239;BA.debugLine="lstBackup.Initialize";
_lstbackup.Initialize();
 //BA.debugLineNum = 241;BA.debugLine="ListEPG 	= mresult.Get(\"begintime\")";
_listepg.setObject((java.util.List)(_mresult.Get((Object)("begintime"))));
 //BA.debugLineNum = 242;BA.debugLine="ListDesc 	= mresult.Get(\"programdescription\")";
_listdesc.setObject((java.util.List)(_mresult.Get((Object)("programdescription"))));
 //BA.debugLineNum = 243;BA.debugLine="lstPrev 	= mresult.Get(\"prevuecode\")";
_vvvvvvvvvvv1.setObject((java.util.List)(_mresult.Get((Object)("prevuecode"))));
 //BA.debugLineNum = 245;BA.debugLine="For g=0 To ListEPG.Size-1";
{
final int step181 = 1;
final int limit181 = (int) (_listepg.getSize()-1);
for (_g = (int) (0); (step181 > 0 && _g <= limit181) || (step181 < 0 && _g >= limit181); _g = ((int)(0 + _g + step181))) {
 //BA.debugLineNum = 246;BA.debugLine="lstBackup.Add(ListEPG.Get(g) & \"#\" & ListDes";
_lstbackup.Add((Object)(BA.ObjectToString(_listepg.Get(_g))+"#"+BA.ObjectToString(_listdesc.Get(_g))+"#"+BA.ObjectToString(_vvvvvvvvvvv1.Get(_g))));
 }
};
 //BA.debugLineNum = 249;BA.debugLine="lstBackup.Sort(False)";
_lstbackup.Sort(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 250;BA.debugLine="ListEPG.Clear";
_listepg.Clear();
 //BA.debugLineNum = 251;BA.debugLine="ListDesc.Clear";
_listdesc.Clear();
 //BA.debugLineNum = 252;BA.debugLine="lstPrev.Clear";
_vvvvvvvvvvv1.Clear();
 //BA.debugLineNum = 254;BA.debugLine="For h=0 To lstBackup.Size-1";
{
final int step188 = 1;
final int limit188 = (int) (_lstbackup.getSize()-1);
for (_h = (int) (0); (step188 > 0 && _h <= limit188) || (step188 < 0 && _h >= limit188); _h = ((int)(0 + _h + step188))) {
 //BA.debugLineNum = 255;BA.debugLine="Dim strBackup() As String";
_strbackup = new String[(int) (0)];
java.util.Arrays.fill(_strbackup,"");
 //BA.debugLineNum = 256;BA.debugLine="strBackup = Regex.Split(\"#\", lstBackup.Get(h";
_strbackup = anywheresoftware.b4a.keywords.Common.Regex.Split("#",BA.ObjectToString(_lstbackup.Get(_h)));
 //BA.debugLineNum = 257;BA.debugLine="ListEPG.Add(strBackup(0))";
_listepg.Add((Object)(_strbackup[(int) (0)]));
 //BA.debugLineNum = 258;BA.debugLine="ListDesc.Add(strBackup(1))";
_listdesc.Add((Object)(_strbackup[(int) (1)]));
 //BA.debugLineNum = 259;BA.debugLine="lstPrev.Add(strBackup(2))";
_vvvvvvvvvvv1.Add((Object)(_strbackup[(int) (2)]));
 }
};
 //BA.debugLineNum = 262;BA.debugLine="svEPG.Panel.RemoveAllViews";
mostCurrent._svepg.getPanel().RemoveAllViews();
 //BA.debugLineNum = 264;BA.debugLine="For i=0 To ListEPG.Size-2";
{
final int step196 = 1;
final int limit196 = (int) (_listepg.getSize()-2);
for (_i = (int) (0); (step196 > 0 && _i <= limit196) || (step196 < 0 && _i >= limit196); _i = ((int)(0 + _i + step196))) {
 //BA.debugLineNum = 265;BA.debugLine="Dim PanEPG 				As Panel";
_panepg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 266;BA.debugLine="Dim Waktu				As List";
_waktu = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 267;BA.debugLine="Dim Tanggal				As String";
_tanggal = "";
 //BA.debugLineNum = 269;BA.debugLine="PanEPG.Initialize(\"\")";
_panepg.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 270;BA.debugLine="PanEPG.LoadLayout(\"EPGDetail\")";
_panepg.LoadLayout("EPGDetail",mostCurrent.activityBA);
 //BA.debugLineNum = 272;BA.debugLine="If Aktif <> 1 Then";
if (_vvvvvvvvvvv3!=1) { 
 //BA.debugLineNum = 273;BA.debugLine="ivEPGDet.Background = bmpImage.Background";
mostCurrent._ivepgdet.setBackground(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv4.getBackground());
 //BA.debugLineNum = 274;BA.debugLine="ivEPGDet.Tag = lstPrev.Get(i)";
mostCurrent._ivepgdet.setTag(_vvvvvvvvvvv1.Get(_i));
 //BA.debugLineNum = 275;BA.debugLine="ivEPGDet.Visible = True";
mostCurrent._ivepgdet.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 277;BA.debugLine="ivEPGDet.Visible = False";
mostCurrent._ivepgdet.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 280;BA.debugLine="svEPG.Panel.AddView(PanEPG, 0, i * lblEPGDet";
mostCurrent._svepg.getPanel().AddView((android.view.View)(_panepg.getObject()),(int) (0),(int) (_i*mostCurrent._lblepgdet.getHeight()),mostCurrent._svepg.getWidth(),mostCurrent._lblepgdet.getHeight());
 //BA.debugLineNum = 282;BA.debugLine="If (i Mod 20) = 0 Then DoEvents";
if ((_i%20)==0) { 
anywheresoftware.b4a.keywords.Common.DoEvents();};
 //BA.debugLineNum = 284;BA.debugLine="Waktu 	= sf.Split(ListEPG.Get(i), \" \")";
_waktu = _sf._vvvvvv5(BA.ObjectToString(_listepg.Get(_i))," ");
 //BA.debugLineNum = 285;BA.debugLine="Tanggal	= sf.Right(Waktu.Get(0), 2) & \".\" &";
_tanggal = _sf._vvvvv7(BA.ObjectToString(_waktu.Get((int) (0))),(long) (2))+"."+_sf._vvvv5(BA.ObjectToString(_waktu.Get((int) (0))),(int) (6),(int) (2))+"."+_sf._vvv6(BA.ObjectToString(_waktu.Get((int) (0))),(long) (4));
 //BA.debugLineNum = 287;BA.debugLine="lblEPGDet.Text = ListDesc.Get(i) & CRLF & Ta";
mostCurrent._lblepgdet.setText((Object)(BA.ObjectToString(_listdesc.Get(_i))+anywheresoftware.b4a.keywords.Common.CRLF+_tanggal+" | "+BA.ObjectToString(_waktu.Get((int) (1)))));
 }
};
 //BA.debugLineNum = 290;BA.debugLine="svEPG.Panel.Height = lblEPGDet.Height * (List";
mostCurrent._svepg.getPanel().setHeight((int) (mostCurrent._lblepgdet.getHeight()*(_listepg.getSize()-1)));
 //BA.debugLineNum = 291;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 break;
case 1:
 //BA.debugLineNum = 296;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 297;BA.debugLine="GifEPG.Visible=True";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 298;BA.debugLine="If ipack < 0 Then";
if (_vvvvvvvvvvvvvvvvvvvvvvvv5<0) { 
 //BA.debugLineNum = 299;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 300;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoPl";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv0,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 302;BA.debugLine="GifEPG.Visible = False";
mostCurrent._gifepg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 303;BA.debugLine="ups.PutExtra(\"LinkURL\", MNChttp.GetString)";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.PutExtra("LinkURL",(Object)(_mnchttp._vvvvvvvvvvvvvvvvvvvvv2()));
 //BA.debugLineNum = 304;BA.debugLine="If ups.IsInitialized Then StartActivity(ups)";
if (mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.IsInitialized()) { 
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvv4.getObject()));};
 };
 break;
}
;
 //BA.debugLineNum = 309;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 };
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return "";
}
public static String  _lvepg_itemclick(int _position,Object _value) throws Exception{
String _strcmd = "";
ottplaymedia.mncplaymedia.com.mnchttpjob _chnjob = null;
 //BA.debugLineNum = 314;BA.debugLine="Sub lvEPG_ItemClick (Position As Int, Value As Obj";
 //BA.debugLineNum = 315;BA.debugLine="Dim strCMD 	As String";
_strcmd = "";
 //BA.debugLineNum = 316;BA.debugLine="Dim ChnJob 	As MNCHttpJob";
_chnjob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 318;BA.debugLine="StopService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 319;BA.debugLine="CancelScheduledService(MNCUtils2Service)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvvvv1.getObject()));
 //BA.debugLineNum = 321;BA.debugLine="strCMD = Fungsi.mpList.Get(\"URLMovieEPG\") & Fungs";
_strcmd = BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("URLMovieEPG")))+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("chancode")))+_vvvvvvvvvv5+"&"+BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("pcode")))+BA.ObjectToString(_vvvvvvvvvvv1.Get(_position));
 //BA.debugLineNum = 322;BA.debugLine="ChnJob.Initialize(\"GetPrevEPG\", Me)";
_chnjob._initialize(processBA,"GetPrevEPG",epglist.getObject());
 //BA.debugLineNum = 324;BA.debugLine="Try";
try { //BA.debugLineNum = 325;BA.debugLine="ChnJob.Download(strCMD)";
_chnjob._download(_strcmd);
 //BA.debugLineNum = 326;BA.debugLine="ChnJob.GetRequest.SetHeader(\"Cache-Control\", \"no";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 327;BA.debugLine="ChnJob.GetRequest.Timeout = DateTime.TicksPerSec";
_chnjob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 //BA.debugLineNum = 328;BA.debugLine="ChnJob.Tag = strCMD";
_chnjob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)(_strcmd);
 } 
       catch (Exception e246) {
			processBA.setLastException(e246); //BA.debugLineNum = 330;BA.debugLine="ChnJob.Release";
_chnjob._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 331;BA.debugLine="Fungsi.mpList.Put(\"Movie EPG\", \"No Movie EPG\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Movie EPG"),(Object)("No Movie EPG"));
 //BA.debugLineNum = 332;BA.debugLine="BDEPGList.Msgbox(Fungsi.TitleBox, Fungsi.NoMovie";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv3.MsgBox(mostCurrent._vvvvvvvvvvvvvvv0._vvvv1,mostCurrent._vvvvvvvvvvvvvvv0._vvvvv6,mostCurrent._vvvvvvvvvvvvvvv0._vvvv2,"","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,mostCurrent._vvvvvvvvvvvvvvv0._vvvvvv5),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 334;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim ChaCode			As String";
_vvvvvvvvvv5 = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim ColCode			As String";
_vvvvvvvvvv6 = "";
 //BA.debugLineNum = 12;BA.debugLine="Dim lstEPG			As List";
_vvvvvvvvvv7 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 13;BA.debugLine="Dim PlayEPG			As List";
_vvvvvvvvvv0 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 14;BA.debugLine="Dim lstPrev			As List";
_vvvvvvvvvvv1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 15;BA.debugLine="Dim BmpPre			As Object";
_vvvvvvvvvvv2 = new Object();
 //BA.debugLineNum = 16;BA.debugLine="Dim Aktif			As Int";
_vvvvvvvvvvv3 = 0;
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
}
