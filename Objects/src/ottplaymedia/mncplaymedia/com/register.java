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

public class register extends Activity implements B4AActivity{
	public static register mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.register");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (register).");
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
		activityBA = new BA(this, layout, processBA, "ottplaymedia.mncplaymedia.com", "ottplaymedia.mncplaymedia.com.register");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.register", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (register) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (register) Resume **");
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
		return register.class;
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
        BA.LogInfo("** Activity (register) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (register) Resume **");
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
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etpass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvv0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etfirst = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etlast = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etmail = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etphone = null;
public anywheresoftware.b4a.objects.EditTextWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _etconfirm = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _ck1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _breg = null;
public flm.b4a.betterdialogs.BetterDialogs _vvvvvvvvvvvvvvvvvvvvvvv5 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spgender = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivreg = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcek = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _vvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = null;
public anywheresoftware.b4a.objects.collections.List _vvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = null;
public anywheresoftware.b4a.objects.collections.Map _vvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.anotherdatepicker _cvbirth = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _ivback = null;
public static int _vvvvvvvvvvv4 = 0;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvvvvvvvvvvvvvv5 = null;
public b4a.example.dateutils _vvvvvvvvvvvvvvv6 = null;
public ottplaymedia.mncplaymedia.com.main _vvvvvvvvvvvvvvv7 = null;
public ottplaymedia.mncplaymedia.com.fungsi _vvvvvvvvvvvvvvv0 = null;
public ottplaymedia.mncplaymedia.com.utama _vvvvvvvvvvvvvvvv1 = null;
public ottplaymedia.mncplaymedia.com.epglist _vvvvvvvvvvvvvvvv2 = null;
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
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdback = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _spgenback = null;
 //BA.debugLineNum = 44;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 49;BA.debugLine="Fungsi.SetDirProg(\"\")";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv1(mostCurrent.activityBA,"");
 //BA.debugLineNum = 50;BA.debugLine="Fungsi.BacaFileConfig";
mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv1(mostCurrent.activityBA);
 //BA.debugLineNum = 54;BA.debugLine="If File.Exists(File.DirAssets, \"config.json\") The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json")) { 
 //BA.debugLineNum = 55;BA.debugLine="If Fungsi.CacahJSON(File.ReadString(File.DirAsse";
if (mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv2(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"config.json"),"NextArray")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 56;BA.debugLine="BDLogin.Msgbox(\"Error\", \"Reading config failed";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox("Error","Reading config failed !","OK","","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"ic_action_warning"),mostCurrent.activityBA);
 //BA.debugLineNum = 57;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 }else {
 //BA.debugLineNum = 60;BA.debugLine="BDLogin.Msgbox(\"Error\", \"Load config failed !\",";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5.MsgBox("Error","Load config failed !","OK","","",mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvv0(mostCurrent.activityBA,"ic_action_warning"),mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 //BA.debugLineNum = 65;BA.debugLine="Activity.LoadLayout(\"NewReg\")";
mostCurrent._activity.LoadLayout("NewReg",mostCurrent.activityBA);
 //BA.debugLineNum = 68;BA.debugLine="Dim cdBack 		As ColorDrawable";
_cdback = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 69;BA.debugLine="Dim spGenBack 	As ColorDrawable";
_spgenback = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 71;BA.debugLine="cdBack.Initialize(Colors.RGB(132, 31, 40), 5)";
_cdback.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (132),(int) (31),(int) (40)),(int) (5));
 //BA.debugLineNum = 72;BA.debugLine="spGenBack.Initialize(Colors.RGB(132, 31, 40), 5)";
_spgenback.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (132),(int) (31),(int) (40)),(int) (5));
 //BA.debugLineNum = 74;BA.debugLine="etFirst.Background = cdBack";
mostCurrent._etfirst.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 75;BA.debugLine="etLast.Background = cdBack";
mostCurrent._etlast.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 76;BA.debugLine="etMail.Background = cdBack";
mostCurrent._etmail.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="etPhone.Background = cdBack";
mostCurrent._etphone.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 79;BA.debugLine="etPass.Background = cdBack";
mostCurrent._etpass.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 80;BA.debugLine="etConfirm.Background = cdBack";
mostCurrent._etconfirm.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="spGender.Background = spGenBack";
mostCurrent._spgender.setBackground((android.graphics.drawable.Drawable)(_spgenback.getObject()));
 //BA.debugLineNum = 85;BA.debugLine="spGender.Add(\"\")";
mostCurrent._spgender.Add("");
 //BA.debugLineNum = 86;BA.debugLine="spGender.Add(\"Male\")";
mostCurrent._spgender.Add("Male");
 //BA.debugLineNum = 87;BA.debugLine="spGender.Add(\"Female\")";
mostCurrent._spgender.Add("Female");
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 98;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 99;BA.debugLine="If cvBirth.IsVisible Then";
if (mostCurrent._cvbirth._vvvvvvvvvvvvvvvvv6()) { 
 //BA.debugLineNum = 100;BA.debugLine="cvBirth.btnCancel_Click 'emulate a click on Can";
mostCurrent._cvbirth._btncancel_click();
 //BA.debugLineNum = 101;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 104;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _breg_click() throws Exception{
ottplaymedia.mncplaymedia.com.mnchttpjob _homejob = null;
String _strenc = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _gen = null;
anywheresoftware.b4a.objects.collections.Map _mregister = null;
 //BA.debugLineNum = 122;BA.debugLine="Sub bReg_Click";
 //BA.debugLineNum = 123;BA.debugLine="Dim HomeJob 	As MNCHttpJob";
_homejob = new ottplaymedia.mncplaymedia.com.mnchttpjob();
 //BA.debugLineNum = 124;BA.debugLine="Dim	strEnc		As String";
_strenc = "";
 //BA.debugLineNum = 125;BA.debugLine="Dim Gen 		As JSONGenerator";
_gen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 126;BA.debugLine="Dim mregister 	As Map";
_mregister = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 129;BA.debugLine="mregister.Initialize";
_mregister.Initialize();
 //BA.debugLineNum = 130;BA.debugLine="mregister.Clear";
_mregister.Clear();
 //BA.debugLineNum = 131;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"firstname\"), etF";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("firstname")),(Object)(mostCurrent._etfirst.getText()));
 //BA.debugLineNum = 132;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"lastname\"), etLa";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("lastname")),(Object)(mostCurrent._etlast.getText()));
 //BA.debugLineNum = 133;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"regemail\"), etMa";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("regemail")),(Object)(mostCurrent._etmail.getText()));
 //BA.debugLineNum = 134;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"handphone\"), etP";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("handphone")),(Object)(mostCurrent._etphone.getText()));
 //BA.debugLineNum = 135;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"regpass\"), etPas";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("regpass")),(Object)(mostCurrent._etpass.getText()));
 //BA.debugLineNum = 136;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"gender\"),  spGen";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("gender")),(Object)(mostCurrent._spgender.getSelectedItem()));
 //BA.debugLineNum = 137;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"homephone\"), \"0\"";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("homephone")),(Object)("0"));
 //BA.debugLineNum = 138;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"id_city\"), \"3173";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("id_city")),(Object)("3173"));
 //BA.debugLineNum = 139;BA.debugLine="mregister.Put(Fungsi.mpList.Get(\"zipcode\"), \"1372";
_mregister.Put(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("zipcode")),(Object)("13720"));
 //BA.debugLineNum = 140;BA.debugLine="Gen.Initialize(mregister)";
_gen.Initialize(_mregister);
 //BA.debugLineNum = 144;BA.debugLine="HomeJob.Initialize(\"Register\", Me)";
_homejob._initialize(processBA,"Register",register.getObject());
 //BA.debugLineNum = 147;BA.debugLine="Try";
try { //BA.debugLineNum = 148;BA.debugLine="HomeJob.Tag = \"Register\"";
_homejob._vvvvvvvvvvvvvvvvvvvvvvv1 = (Object)("Register");
 //BA.debugLineNum = 149;BA.debugLine="strEnc = Fungsi.ProsesEnkripsi(Gen.ToPrettyStri";
_strenc = mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvv0(mostCurrent.activityBA,_gen.ToPrettyString((int) (18)));
 //BA.debugLineNum = 150;BA.debugLine="If strEnc = \"Enkripsi Failed\" Then Return";
if ((_strenc).equals("Enkripsi Failed")) { 
if (true) return "";};
 //BA.debugLineNum = 151;BA.debugLine="HomeJob.PostString(Fungsi.mpList.Get(\"RegisterU";
_homejob._vvvvvvvvvvvvvvvvvvvvv7(BA.ObjectToString(mostCurrent._vvvvvvvvvvvvvvv0._v5.Get((Object)("RegisterURL"))),"teks="+_strenc);
 //BA.debugLineNum = 152;BA.debugLine="HomeJob.GetRequest.SetHeader(\"Cache-Control\", \"";
_homejob._vvvvvvvvvvvvvvvvvvvvv1().SetHeader("Cache-Control","no-cache, must-revalidate");
 //BA.debugLineNum = 153;BA.debugLine="HomeJob.GetRequest.Timeout = DateTime.TicksPerS";
_homejob._vvvvvvvvvvvvvvvvvvvvv1().setTimeout((int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond*60));
 } 
       catch (Exception e102) {
			processBA.setLastException(e102); //BA.debugLineNum = 155;BA.debugLine="Msgbox2(\"Register Failure !\", \"Moviebay\", \"OK\",";
anywheresoftware.b4a.keywords.Common.Msgbox2("Register Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 158;BA.debugLine="End Sub";
return "";
}
public static String  _ck1_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub  ck1_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 118;BA.debugLine="If Checked = True Then bReg.Enabled = True Else b";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._breg.setEnabled(anywheresoftware.b4a.keywords.Common.True);}
else {
mostCurrent._breg.setEnabled(anywheresoftware.b4a.keywords.Common.False);};
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _ckbox_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 248;BA.debugLine="Sub ckBox_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 250;BA.debugLine="End Sub";
return "";
}
public static String  _cvbirth_closed(boolean _cancelled,long _date) throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Sub cvBirth_Closed (Cancelled As Boolean, Date As";
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private ivMov 		As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private etPass 		As EditText";
mostCurrent._etpass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private etCfpass	As EditText";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvv0 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lblPass 	As Label";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private etFirst 	As EditText";
mostCurrent._etfirst = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private etLast 		As EditText";
mostCurrent._etlast = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private etMail 		As EditText";
mostCurrent._etmail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private etPhone 	As EditText";
mostCurrent._etphone = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private etGender 	As EditText";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private etBirth 	As EditText";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private etConfirm 	As EditText";
mostCurrent._etconfirm = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private ivBackrg 	As ImageView";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private ck1 		As CheckBox";
mostCurrent._ck1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private bReg 		As Button";
mostCurrent._breg = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private BDLogin		As BetterDialogs";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvv5 = new flm.b4a.betterdialogs.BetterDialogs();
 //BA.debugLineNum = 32;BA.debugLine="Private spGender 	As Spinner";
mostCurrent._spgender = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private ivReg 		As ImageView";
mostCurrent._ivreg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private spBirth 	As Spinner";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv5 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private lblCek 		As Label";
mostCurrent._lblcek = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private ckBox 		As CheckBox";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv6 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private lstCate 	As List";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 38;BA.debugLine="Private coba 		As Map";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv0 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 39;BA.debugLine="Private cvBirth 	As AnotherDatePicker";
mostCurrent._cvbirth = new ottplaymedia.mncplaymedia.com.anotherdatepicker();
 //BA.debugLineNum = 40;BA.debugLine="Private ivBack		As ImageView";
mostCurrent._ivback = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Dim flag As Int = 0";
_vvvvvvvvvvv4 = (int) (0);
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _ivback_click() throws Exception{
 //BA.debugLineNum = 254;BA.debugLine="Sub ivBack_Click";
 //BA.debugLineNum = 255;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._vvvvvvvvvvvvvvv7.getObject()));
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return "";
}
public static String  _ivbackrg_click() throws Exception{
 //BA.debugLineNum = 114;BA.debugLine="Sub ivBackrg_Click";
 //BA.debugLineNum = 115;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(ottplaymedia.mncplaymedia.com.mnchttpjob _mnchttp) throws Exception{
adr.stringfunctions.stringfunctions _sf = null;
String _stmp = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _mresult = null;
byte[] _btmp = null;
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bctmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 159;BA.debugLine="Sub JobDone (MNChttp As MNCHttpJob)";
 //BA.debugLineNum = 161;BA.debugLine="Dim sf			As StringFunctions";
_sf = new adr.stringfunctions.stringfunctions();
 //BA.debugLineNum = 162;BA.debugLine="Dim sTmp 		As String";
_stmp = "";
 //BA.debugLineNum = 163;BA.debugLine="Dim parser 		As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 164;BA.debugLine="Dim mresult		As Map";
_mresult = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 165;BA.debugLine="Dim bTmp()		As Byte";
_btmp = new byte[(int) (0)];
;
 //BA.debugLineNum = 166;BA.debugLine="Dim bcTmp		As ByteConverter";
_bctmp = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 167;BA.debugLine="Dim su 			As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 171;BA.debugLine="If MNChttp.Success = False Then";
if (_mnchttp._vvvvvvvvvvvvvvvvvvvvvv2==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 172;BA.debugLine="If (sf.InString(MNChttp.ErrorMessage, \"timed out";
if ((_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"timed out")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"refused")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Unable to resolve host")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"UnknownHostException")>0) || (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"FileNotFound")>0)) { 
 //BA.debugLineNum = 176;BA.debugLine="Msgbox2(\"Offline Connection or Server Down\", \"M";
anywheresoftware.b4a.keywords.Common.Msgbox2("Offline Connection or Server Down","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 178;BA.debugLine="If sf.InString(MNChttp.ErrorMessage, \"Not Found";
if (_sf._vvv4(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv5,"Not Found")<0) { 
 //BA.debugLineNum = 180;BA.debugLine="Msgbox2(\"Unknown Error\", \"Moviebay\", \"OK\", \"\",";
anywheresoftware.b4a.keywords.Common.Msgbox2("Unknown Error","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 184;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Offline\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Offline"));
 //BA.debugLineNum = 185;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 }else {
 //BA.debugLineNum = 189;BA.debugLine="Try";
try { //BA.debugLineNum = 190;BA.debugLine="bTmp = su.DecodeBase64(MNChttp.GetString)";
_btmp = _su.DecodeBase64(_mnchttp._vvvvvvvvvvvvvvvvvvvvv2());
 //BA.debugLineNum = 191;BA.debugLine="sTmp = bcTmp.StringFromBytes(bTmp, \"UTF8\")";
_stmp = _bctmp.StringFromBytes(_btmp,"UTF8");
 //BA.debugLineNum = 192;BA.debugLine="parser.Initialize(Fungsi.UnWrap(sTmp))";
_parser.Initialize(mostCurrent._vvvvvvvvvvvvvvv0._vvvvvvvvv5(mostCurrent.activityBA,_stmp));
 //BA.debugLineNum = 193;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 } 
       catch (Exception e130) {
			processBA.setLastException(e130); //BA.debugLineNum = 195;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\",";
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 //BA.debugLineNum = 196;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 199;BA.debugLine="Try";
try { //BA.debugLineNum = 200;BA.debugLine="mresult.Initialize";
_mresult.Initialize();
 //BA.debugLineNum = 201;BA.debugLine="mresult = parser.NextObject";
_mresult = _parser.NextObject();
 } 
       catch (Exception e137) {
			processBA.setLastException(e137); //BA.debugLineNum = 203;BA.debugLine="Msgbox2(\"Process Failure !\", \"Moviebay\", \"OK\",";
anywheresoftware.b4a.keywords.Common.Msgbox2("Process Failure !","Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 //BA.debugLineNum = 204;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 207;BA.debugLine="If mresult.Get(\"Result\") <> \"0\" Then";
if ((_mresult.Get((Object)("Result"))).equals((Object)("0")) == false) { 
 //BA.debugLineNum = 208;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Moviebay\", \"OK";
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Moviebay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 210;BA.debugLine="Fungsi.mpList.Put(\"Koneksi\", \"Online\")";
mostCurrent._vvvvvvvvvvvvvvv0._v5.Put((Object)("Koneksi"),(Object)("Online"));
 //BA.debugLineNum = 211;BA.debugLine="MNChttp.Release";
_mnchttp._vvvvvvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 213;BA.debugLine="Select MNChttp.JobName";
switch (BA.switchObjectToInt(_mnchttp._vvvvvvvvvvvvvvvvvvvvvv1,"Register")) {
case 0:
 //BA.debugLineNum = 217;BA.debugLine="Msgbox2(mresult.Get(\"Message\"), \"Movibay\", \"OK\"";
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToString(_mresult.Get((Object)("Message"))),"Movibay","OK","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"warning.png").getObject()),mostCurrent.activityBA);
 //BA.debugLineNum = 225;BA.debugLine="If lstCate.IsInitialized = False Then lstCate.I";
if (mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv7.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv7.Initialize();};
 //BA.debugLineNum = 226;BA.debugLine="lstCate.Clear";
mostCurrent._vvvvvvvvvvvvvvvvvvvvvvvvvvvv7.Clear();
 break;
}
;
 };
 };
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _lblcek_click() throws Exception{
 //BA.debugLineNum = 258;BA.debugLine="Sub lblCek_Click";
 //BA.debugLineNum = 259;BA.debugLine="If flag  = 0 Then";
if (_vvvvvvvvvvv4==0) { 
 //BA.debugLineNum = 260;BA.debugLine="flag = 1";
_vvvvvvvvvvv4 = (int) (1);
 //BA.debugLineNum = 261;BA.debugLine="ck1.Checked = True";
mostCurrent._ck1.setChecked(anywheresoftware.b4a.keywords.Common.True);
 }else if(_vvvvvvvvvvv4==1) { 
 //BA.debugLineNum = 263;BA.debugLine="flag = 0";
_vvvvvvvvvvv4 = (int) (0);
 //BA.debugLineNum = 264;BA.debugLine="ck1.Checked = False";
mostCurrent._ck1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 266;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _spbirth_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Sub spBirth_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return "";
}
public static String  _spgender_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 234;BA.debugLine="Sub spGender_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 236;BA.debugLine="If Value = \"Male\" Then";
if ((_value).equals((Object)("Male"))) { 
 }else if((_value).equals((Object)("Female"))) { 
 }else {
 };
 //BA.debugLineNum = 244;BA.debugLine="End Sub";
return "";
}
}
