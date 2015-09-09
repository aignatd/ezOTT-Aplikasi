package ottplaymedia.mncplaymedia.com;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class anotherdatepicker extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "ottplaymedia.mncplaymedia.com.anotherdatepicker");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "ottplaymedia.mncplaymedia.com.anotherdatepicker",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.PanelWrapper _vvvvvvvvvvvvvvvvvv1 = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _vvvvvvvvvvvvvvvvvv2 = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _vvvvvvvvvvvvvvvvvv3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _dayspanel = null;
public int _vvvvvvvvvvvvvvvvvv4 = 0;
public int _vvvvvvvvvvvvvvvvvv5 = 0;
public anywheresoftware.b4a.objects.SpinnerWrapper _months = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _years = null;
public float _vvvvvvvvvvvvvvvvvv6 = 0f;
public float _vvvvvvvvvvvvvvvvvv7 = 0f;
public float _vvvvvvvvvvvvvvvvvv0 = 0f;
public int _action_up = 0;
public int _action_move = 0;
public int _action_down = 0;
public int _vvvvvvvvvvvvvvvvvvv1 = 0;
public anywheresoftware.b4a.objects.PanelWrapper _dayspanelbackground = null;
public int _vvvvvvvvvvvvvvvvvvv2 = 0;
public int _vvvvvvvvvvvvvvvvvvv3 = 0;
public int _vvvvvvvvvvvvvvvvvvv4 = 0;
public int _vvvvvvvvvvvvvvvvvvv5 = 0;
public anywheresoftware.b4a.objects.LabelWrapper _lblselectedday = null;
public long _vvvvvvvvvvvvvvvvvvv6 = 0L;
public anywheresoftware.b4a.objects.LabelWrapper _vvvvvvvvvvvvvvvvvvv7 = null;
public int _vvvvvvvvvvvvvvvvvvv0 = 0;
public int _vvvvvvvvvvvvvvvvvvvv1 = 0;
public int _vvvvvvvvvvvvvvvvvvvv2 = 0;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.LabelWrapper[] _vvvvvvvvvvvvvvvvvvvv3 = null;
public Object _vvvvvvvvvvvvvvvvvvvv4 = null;
public String _vvvvvvvvvvvvvvvvvvvv5 = "";
public boolean _vvvvvvvvvvvvvvvvvvvv6 = false;
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
public ottplaymedia.mncplaymedia.com.mncutils2service _vvvvvvvvvvvvvvvvv1 = null;
public String  _addtoactivity(anywheresoftware.b4a.objects.ActivityWrapper _act,anywheresoftware.b4a.objects.LabelWrapper _lbl) throws Exception{
anywheresoftware.b4a.objects.EditTextWrapper _et = null;
int _y = 0;
String _m = "";
int _i = 0;
String _d = "";
 //BA.debugLineNum = 63;BA.debugLine="Public Sub AddToActivity(ACT As Activity, Lbl As L";
 //BA.debugLineNum = 64;BA.debugLine="waitForAddToActivity = False";
_vvvvvvvvvvvvvvvvvvvv6 = __c.False;
 //BA.debugLineNum = 65;BA.debugLine="holder.Initialize(\"holder\")";
_vvvvvvvvvvvvvvvvvv1.Initialize(ba,"holder");
 //BA.debugLineNum = 66;BA.debugLine="holder.Visible = False";
_vvvvvvvvvvvvvvvvvv1.setVisible(__c.False);
 //BA.debugLineNum = 67;BA.debugLine="holder.Color = Colors.Transparent";
_vvvvvvvvvvvvvvvvvv1.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 68;BA.debugLine="ACT.AddView(holder, 0, 0, 100%x, 100%y)";
_act.AddView((android.view.View)(_vvvvvvvvvvvvvvvvvv1.getObject()),(int) (0),(int) (0),__c.PerXToCurrent((float) (100),ba),__c.PerYToCurrent((float) (100),ba));
 //BA.debugLineNum = 69;BA.debugLine="holder.LoadLayout(\"DatePicker\")";
_vvvvvvvvvvvvvvvvvv1.LoadLayout("DatePicker",ba);
 //BA.debugLineNum = 70;BA.debugLine="daysNames = Array As Label(Label1, Label2, Label3";
_vvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.LabelWrapper[]{_label1,_label2,_label3,_label4,_label5,_label6,_label7};
 //BA.debugLineNum = 71;BA.debugLine="Dim et As EditText";
_et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 72;BA.debugLine="et.Initialize(\"\")";
_et.Initialize(ba,"");
 //BA.debugLineNum = 73;BA.debugLine="Lbl.BACKGROUND = et.BACKGROUND";
_lbl.setBackground(_et.getBackground());
 //BA.debugLineNum = 74;BA.debugLine="cvs.Initialize(DaysPanel)";
_vvvvvvvvvvvvvvvvvv2.Initialize((android.view.View)(_dayspanel.getObject()));
 //BA.debugLineNum = 75;BA.debugLine="cvsBackground.Initialize(DaysPanelBackground)";
_vvvvvvvvvvvvvvvvvv3.Initialize((android.view.View)(_dayspanelbackground.getObject()));
 //BA.debugLineNum = 76;BA.debugLine="month = DateTime.GetMonth(DateTime.Now)";
_vvvvvvvvvvvvvvvvvv4 = __c.DateTime.GetMonth(__c.DateTime.getNow());
 //BA.debugLineNum = 77;BA.debugLine="year = DateTime.GetYear(DateTime.Now)";
_vvvvvvvvvvvvvvvvvv5 = __c.DateTime.GetYear(__c.DateTime.getNow());
 //BA.debugLineNum = 78;BA.debugLine="For y = 1900 To 2100";
{
final int step64 = 1;
final int limit64 = (int) (2100);
for (_y = (int) (1900); (step64 > 0 && _y <= limit64) || (step64 < 0 && _y >= limit64); _y = ((int)(0 + _y + step64))) {
 //BA.debugLineNum = 79;BA.debugLine="Years.Add(y)";
_years.Add(BA.NumberToString(_y));
 }
};
 //BA.debugLineNum = 81;BA.debugLine="For Each m As String In DateUtils.GetMonthsNames";
final anywheresoftware.b4a.BA.IterableList group67 = _vvvvvvvvvvvvvvv6._getmonthsnames(ba);
final int groupLen67 = group67.getSize();
for (int index67 = 0;index67 < groupLen67 ;index67++){
_m = BA.ObjectToString(group67.Get(index67));
 //BA.debugLineNum = 82;BA.debugLine="Months.Add(m)";
_months.Add(_m);
 }
;
 //BA.debugLineNum = 84;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 85;BA.debugLine="For Each D As String In DateUtils.GetDaysNames";
final anywheresoftware.b4a.BA.IterableList group71 = _vvvvvvvvvvvvvvv6._getdaysnames(ba);
final int groupLen71 = group71.getSize();
for (int index71 = 0;index71 < groupLen71 ;index71++){
_d = BA.ObjectToString(group71.Get(index71));
 //BA.debugLineNum = 86;BA.debugLine="daysNames(i).Text = D.SubString2(0, 2)";
_vvvvvvvvvvvvvvvvvvvv3[_i].setText((Object)(_d.substring((int) (0),(int) (2))));
 //BA.debugLineNum = 87;BA.debugLine="i = i + 1";
_i = (int) (_i+1);
 }
;
 //BA.debugLineNum = 89;BA.debugLine="SetDate(DateTime.Now, False)";
_setdate(__c.DateTime.getNow(),__c.False);
 //BA.debugLineNum = 90;BA.debugLine="vCorrection = cvs.MeasureStringHeight(\"1\", Typefa";
_vvvvvvvvvvvvvvvvvv0 = (float) (_vvvvvvvvvvvvvvvvvv2.MeasureStringHeight("1",__c.Typeface.DEFAULT_BOLD,_label1.getTextSize())/(double)2);
 //BA.debugLineNum = 91;BA.debugLine="boxW = cvs.Bitmap.Width / 7";
_vvvvvvvvvvvvvvvvvv6 = (float) (_vvvvvvvvvvvvvvvvvv2.getBitmap().getWidth()/(double)7);
 //BA.debugLineNum = 92;BA.debugLine="boxH = cvs.Bitmap.Height / 6";
_vvvvvvvvvvvvvvvvvv7 = (float) (_vvvvvvvvvvvvvvvvvv2.getBitmap().getHeight()/(double)6);
 //BA.debugLineNum = 93;BA.debugLine="lblSelectedDay.Visible = False";
_lblselectedday.setVisible(__c.False);
 //BA.debugLineNum = 94;BA.debugLine="DrawDays";
_vvvvvvvvvvvvvvvvv3();
 //BA.debugLineNum = 95;BA.debugLine="targetLabel = Lbl";
_vvvvvvvvvvvvvvvvvvv7 = _lbl;
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Public Sub btnCancel_Click";
 //BA.debugLineNum = 199;BA.debugLine="CallSub3(mTarget, mEventName & \"_Closed\", True, G";
__c.CallSubNew3(ba,_vvvvvvvvvvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvv5+"_Closed",(Object)(__c.True),(Object)(_vvvvvvvvvvvvvvvvv4()));
 //BA.debugLineNum = 200;BA.debugLine="Hide";
_vvvvvvvvvvvvvvvvv5();
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public String  _btntoday_click() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Private Sub btnToday_Click";
 //BA.debugLineNum = 191;BA.debugLine="SetDate(DateTime.Now, True)";
_setdate(__c.DateTime.getNow(),__c.True);
 //BA.debugLineNum = 192;BA.debugLine="Log(\"before\")";
__c.Log("before");
 //BA.debugLineNum = 193;BA.debugLine="CallSub3(mTarget, mEventName & \"_Closed\", False,";
__c.CallSubNew3(ba,_vvvvvvvvvvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvv5+"_Closed",(Object)(__c.False),(Object)(_vvvvvvvvvvvvvvvvv4()));
 //BA.debugLineNum = 194;BA.debugLine="Log(\"after\")";
__c.Log("after");
 //BA.debugLineNum = 195;BA.debugLine="Hide";
_vvvvvvvvvvvvvvvvv5();
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 4;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Private holder As Panel";
_vvvvvvvvvvvvvvvvvv1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private cvs, cvsBackground As Canvas";
_vvvvvvvvvvvvvvvvvv2 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
_vvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private DaysPanel As Panel";
_dayspanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private month, year As Int";
_vvvvvvvvvvvvvvvvvv4 = 0;
_vvvvvvvvvvvvvvvvvv5 = 0;
 //BA.debugLineNum = 9;BA.debugLine="Private Months As Spinner";
_months = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private Years As Spinner";
_years = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private boxW, boxH As Float";
_vvvvvvvvvvvvvvvvvv6 = 0f;
_vvvvvvvvvvvvvvvvvv7 = 0f;
 //BA.debugLineNum = 12;BA.debugLine="Private vCorrection As Float";
_vvvvvvvvvvvvvvvvvv0 = 0f;
 //BA.debugLineNum = 13;BA.debugLine="Private ACTION_UP = 1, ACTION_MOVE = 2, ACTION_DO";
_action_up = (int) (1);
_action_move = (int) (2);
_action_down = (int) (0);
 //BA.debugLineNum = 14;BA.debugLine="Private tempSelectedDay As Int";
_vvvvvvvvvvvvvvvvvvv1 = 0;
 //BA.debugLineNum = 15;BA.debugLine="Private DaysPanelBackground As Panel";
_dayspanelbackground = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private dayOfWeekOffset As Int";
_vvvvvvvvvvvvvvvvvvv2 = 0;
 //BA.debugLineNum = 17;BA.debugLine="Private daysInMonth As Int";
_vvvvvvvvvvvvvvvvvvv3 = 0;
 //BA.debugLineNum = 18;BA.debugLine="Private tempSelectedColor As Int = Colors.Cyan";
_vvvvvvvvvvvvvvvvvvv4 = __c.Colors.Cyan;
 //BA.debugLineNum = 19;BA.debugLine="Private selectedColor As Int = Colors.RGB(144, 17";
_vvvvvvvvvvvvvvvvvvv5 = __c.Colors.RGB((int) (144),(int) (176),(int) (248));
 //BA.debugLineNum = 20;BA.debugLine="Private lblSelectedDay As Label";
_lblselectedday = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private selectedDate As Long";
_vvvvvvvvvvvvvvvvvvv6 = 0L;
 //BA.debugLineNum = 22;BA.debugLine="Private targetLabel As Label";
_vvvvvvvvvvvvvvvvvvv7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private selectedYear, selectedMonth, selectedDay";
_vvvvvvvvvvvvvvvvvvv0 = 0;
_vvvvvvvvvvvvvvvvvvvv1 = 0;
_vvvvvvvvvvvvvvvvvvvv2 = 0;
 //BA.debugLineNum = 24;BA.debugLine="Private Label1 As Label";
_label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Label2 As Label";
_label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Label3 As Label";
_label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Label4 As Label";
_label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Label5 As Label";
_label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Label6 As Label";
_label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Label7 As Label";
_label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private daysNames() As Label";
_vvvvvvvvvvvvvvvvvvvv3 = new anywheresoftware.b4a.objects.LabelWrapper[(int) (0)];
{
int d0 = _vvvvvvvvvvvvvvvvvvvv3.length;
for (int i0 = 0;i0 < d0;i0++) {
_vvvvvvvvvvvvvvvvvvvv3[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
 //BA.debugLineNum = 32;BA.debugLine="Private mTarget As Object";
_vvvvvvvvvvvvvvvvvvvv4 = new Object();
 //BA.debugLineNum = 33;BA.debugLine="Private mEventName As String";
_vvvvvvvvvvvvvvvvvvvv5 = "";
 //BA.debugLineNum = 34;BA.debugLine="Private waitForAddToActivity As Boolean";
_vvvvvvvvvvvvvvvvvvvv6 = false;
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public String  _dayspanel_touch(int _action,float _x,float _y) throws Exception{
int _boxx = 0;
int _boxy = 0;
int _newselectedday = 0;
boolean _validday = false;
 //BA.debugLineNum = 158;BA.debugLine="Private Sub DaysPanel_Touch (ACTION As Int, X As F";
 //BA.debugLineNum = 159;BA.debugLine="Dim boxX = X / boxW, boxY = Y / boxH As Int";
_boxx = (int) (_x/(double)_vvvvvvvvvvvvvvvvvv6);
_boxy = (int) (_y/(double)_vvvvvvvvvvvvvvvvvv7);
 //BA.debugLineNum = 160;BA.debugLine="Dim newSelectedDay As Int = boxY * 7 + boxX + 1 -";
_newselectedday = (int) (_boxy*7+_boxx+1-_vvvvvvvvvvvvvvvvvvv2);
 //BA.debugLineNum = 161;BA.debugLine="Dim validDay As Boolean = newSelectedDay > 0 AND";
_validday = _newselectedday>0 && _newselectedday<=_vvvvvvvvvvvvvvvvvvv3;
 //BA.debugLineNum = 162;BA.debugLine="If ACTION = ACTION_DOWN OR ACTION = ACTION_MOVE T";
if (_action==_action_down || _action==_action_move) { 
 //BA.debugLineNum = 163;BA.debugLine="If newSelectedDay = tempSelectedDay Then Return";
if (_newselectedday==_vvvvvvvvvvvvvvvvvvv1) { 
if (true) return "";};
 //BA.debugLineNum = 164;BA.debugLine="cvsBackground.DrawColor(Colors.Transparent) 'cle";
_vvvvvvvvvvvvvvvvvv3.DrawColor(__c.Colors.Transparent);
 //BA.debugLineNum = 165;BA.debugLine="tempSelectedDay = newSelectedDay";
_vvvvvvvvvvvvvvvvvvv1 = _newselectedday;
 //BA.debugLineNum = 166;BA.debugLine="If validDay Then";
if (_validday) { 
 //BA.debugLineNum = 167;BA.debugLine="DrawBox(cvsBackground, tempSelectedColor, boxX,";
_vvvvvvvvvvvvvvvvv2(_vvvvvvvvvvvvvvvvvv3,_vvvvvvvvvvvvvvvvvvv4,_boxx,_boxy);
 //BA.debugLineNum = 168;BA.debugLine="lblSelectedDay.Text = newSelectedDay";
_lblselectedday.setText((Object)(_newselectedday));
 //BA.debugLineNum = 169;BA.debugLine="lblSelectedDay.Visible = True";
_lblselectedday.setVisible(__c.True);
 }else {
 //BA.debugLineNum = 171;BA.debugLine="lblSelectedDay.Visible = False";
_lblselectedday.setVisible(__c.False);
 };
 }else if(_action==_action_up) { 
 //BA.debugLineNum = 174;BA.debugLine="lblSelectedDay.Visible = False";
_lblselectedday.setVisible(__c.False);
 //BA.debugLineNum = 175;BA.debugLine="cvsBackground.DrawColor(Colors.Transparent)";
_vvvvvvvvvvvvvvvvvv3.DrawColor(__c.Colors.Transparent);
 //BA.debugLineNum = 176;BA.debugLine="If validDay Then";
if (_validday) { 
 //BA.debugLineNum = 177;BA.debugLine="SelectDay(newSelectedDay, True)";
_vvvvvvvvvvvvvvvvv7(_newselectedday,__c.True);
 //BA.debugLineNum = 178;BA.debugLine="CallSub3(mTarget, mEventName & \"_Closed\", False";
__c.CallSubNew3(ba,_vvvvvvvvvvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvvvv5+"_Closed",(Object)(__c.False),(Object)(_vvvvvvvvvvvvvvvvv4()));
 //BA.debugLineNum = 179;BA.debugLine="Hide";
_vvvvvvvvvvvvvvvvv5();
 };
 };
 //BA.debugLineNum = 182;BA.debugLine="DaysPanelBackground.Invalidate";
_dayspanelbackground.Invalidate();
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return "";
}
public String  _designercreateview(anywheresoftware.b4a.objects.PanelWrapper _base,anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _newlbl = null;
anywheresoftware.b4a.objects.ActivityWrapper _act = null;
 //BA.debugLineNum = 43;BA.debugLine="Public Sub DesignerCreateView(base As Panel, lbl A";
 //BA.debugLineNum = 45;BA.debugLine="Dim newLbl As Label";
_newlbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="newLbl.Initialize(\"lbl\")";
_newlbl.Initialize(ba,"lbl");
 //BA.debugLineNum = 47;BA.debugLine="newLbl.TextSize = lbl.TextSize";
_newlbl.setTextSize(_lbl.getTextSize());
 //BA.debugLineNum = 48;BA.debugLine="newLbl.TextColor = lbl.TextColor";
_newlbl.setTextColor(_lbl.getTextColor());
 //BA.debugLineNum = 49;BA.debugLine="base.AddView(newLbl, 0, 0, base.Width, base.Heigh";
_base.AddView((android.view.View)(_newlbl.getObject()),(int) (0),(int) (0),_base.getWidth(),_base.getHeight());
 //BA.debugLineNum = 50;BA.debugLine="Dim act As Activity = props.Get(\"activity\")";
_act = new anywheresoftware.b4a.objects.ActivityWrapper();
_act.setObject((anywheresoftware.b4a.BALayout)(_props.Get((Object)("activity"))));
 //BA.debugLineNum = 51;BA.debugLine="waitForAddToActivity = True";
_vvvvvvvvvvvvvvvvvvvv6 = __c.True;
 //BA.debugLineNum = 54;BA.debugLine="CallSubDelayed3(Me, \"AddToActivity\",act , newLbl)";
__c.CallSubDelayed3(ba,this,"AddToActivity",(Object)(_act),(Object)(_newlbl));
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvv2(anywheresoftware.b4a.objects.drawable.CanvasWrapper _c,int _clr,int _x,int _y) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _r = null;
 //BA.debugLineNum = 152;BA.debugLine="Private Sub DrawBox(c As Canvas, clr As Int, x As";
 //BA.debugLineNum = 153;BA.debugLine="Dim r As Rect";
_r = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 154;BA.debugLine="r.Initialize(x * boxW, y * boxH, (x + 1) * boxW,";
_r.Initialize((int) (_x*_vvvvvvvvvvvvvvvvvv6),(int) (_y*_vvvvvvvvvvvvvvvvvv7),(int) ((_x+1)*_vvvvvvvvvvvvvvvvvv6),(int) ((_y+1)*_vvvvvvvvvvvvvvvvvv7));
 //BA.debugLineNum = 155;BA.debugLine="c.DrawRect(r, clr, True, 0)";
_c.DrawRect((android.graphics.Rect)(_r.getObject()),_clr,__c.True,(float) (0));
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvv3() throws Exception{
long _firstday = 0L;
int _day = 0;
int _row = 0;
 //BA.debugLineNum = 115;BA.debugLine="Private Sub DrawDays";
 //BA.debugLineNum = 116;BA.debugLine="cvsBackground.DrawColor(Colors.Transparent)";
_vvvvvvvvvvvvvvvvvv3.DrawColor(__c.Colors.Transparent);
 //BA.debugLineNum = 117;BA.debugLine="cvs.DrawColor(Colors.Transparent)";
_vvvvvvvvvvvvvvvvvv2.DrawColor(__c.Colors.Transparent);
 //BA.debugLineNum = 118;BA.debugLine="Dim firstDay As Long = DateUtils.SetDate(year, mo";
_firstday = (long) (_vvvvvvvvvvvvvvv6._setdate(ba,_vvvvvvvvvvvvvvvvvv5,_vvvvvvvvvvvvvvvvvv4,(int) (1))-1);
 //BA.debugLineNum = 119;BA.debugLine="dayOfWeekOffset = DateTime.GetDayOfWeek(firstDay)";
_vvvvvvvvvvvvvvvvvvv2 = (int) (__c.DateTime.GetDayOfWeek(_firstday)%7);
 //BA.debugLineNum = 120;BA.debugLine="daysInMonth = DateUtils.NumberOfDaysInMonth(month";
_vvvvvvvvvvvvvvvvvvv3 = _vvvvvvvvvvvvvvv6._numberofdaysinmonth(ba,_vvvvvvvvvvvvvvvvvv4,_vvvvvvvvvvvvvvvvvv5);
 //BA.debugLineNum = 121;BA.debugLine="If year = selectedYear AND month = selectedMonth";
if (_vvvvvvvvvvvvvvvvvv5==_vvvvvvvvvvvvvvvvvvv0 && _vvvvvvvvvvvvvvvvvv4==_vvvvvvvvvvvvvvvvvvvv1) { 
 //BA.debugLineNum = 123;BA.debugLine="DrawBox(cvs, selectedColor, (selectedDay - 1 + d";
_vvvvvvvvvvvvvvvvv2(_vvvvvvvvvvvvvvvvvv2,_vvvvvvvvvvvvvvvvvvv5,(int) ((_vvvvvvvvvvvvvvvvvvvv2-1+_vvvvvvvvvvvvvvvvvvv2)%7),(int) ((_vvvvvvvvvvvvvvvvvvvv2-1+_vvvvvvvvvvvvvvvvvvv2)/(double)7));
 };
 //BA.debugLineNum = 126;BA.debugLine="For day = 1 To daysInMonth";
{
final int step106 = 1;
final int limit106 = _vvvvvvvvvvvvvvvvvvv3;
for (_day = (int) (1); (step106 > 0 && _day <= limit106) || (step106 < 0 && _day >= limit106); _day = ((int)(0 + _day + step106))) {
 //BA.debugLineNum = 127;BA.debugLine="Dim row As Int = (day - 1 + dayOfWeekOffset) / 7";
_row = (int) ((_day-1+_vvvvvvvvvvvvvvvvvvv2)/(double)7);
 //BA.debugLineNum = 128;BA.debugLine="cvs.DrawText(day, (((dayOfWeekOffset + day - 1)";
_vvvvvvvvvvvvvvvvvv2.DrawText(ba,BA.NumberToString(_day),(float) ((((_vvvvvvvvvvvvvvvvvvv2+_day-1)%7)+0.5)*_vvvvvvvvvvvvvvvvvv6),(float) ((_row+0.5)*_vvvvvvvvvvvvvvvvvv7+_vvvvvvvvvvvvvvvvvv0),__c.Typeface.DEFAULT_BOLD,_label1.getTextSize(),__c.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }
};
 //BA.debugLineNum = 137;BA.debugLine="DaysPanel.Invalidate";
_dayspanel.Invalidate();
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public long  _vvvvvvvvvvvvvvvvv4() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Public Sub GetDate As Long";
 //BA.debugLineNum = 99;BA.debugLine="Return selectedDate";
if (true) return _vvvvvvvvvvvvvvvvvvv6;
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return 0L;
}
public String  _vvvvvvvvvvvvvvvvv5() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Public Sub Hide";
 //BA.debugLineNum = 149;BA.debugLine="holder.Visible = False";
_vvvvvvvvvvvvvvvvvv1.setVisible(__c.False);
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public String  _holder_click() throws Exception{
 //BA.debugLineNum = 215;BA.debugLine="Private Sub holder_Click";
 //BA.debugLineNum = 216;BA.debugLine="btnCancel_Click";
_btncancel_click();
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _target,String _eventname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 38;BA.debugLine="Public Sub Initialize (Target As Object, EventName";
 //BA.debugLineNum = 39;BA.debugLine="mTarget = Target";
_vvvvvvvvvvvvvvvvvvvv4 = _target;
 //BA.debugLineNum = 40;BA.debugLine="mEventName = EventName";
_vvvvvvvvvvvvvvvvvvvv5 = _eventname;
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public boolean  _vvvvvvvvvvvvvvvvv6() throws Exception{
 //BA.debugLineNum = 212;BA.debugLine="Public Sub IsVisible As Boolean";
 //BA.debugLineNum = 213;BA.debugLine="Return holder.Visible";
if (true) return _vvvvvvvvvvvvvvvvvv1.getVisible();
 //BA.debugLineNum = 214;BA.debugLine="End Sub";
return false;
}
public String  _lbl_click() throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Private Sub lbl_Click";
 //BA.debugLineNum = 58;BA.debugLine="Show";
_vvvvvvvvvvvvvvvvv0();
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public String  _months_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Private Sub Months_ItemClick (Position As Int, Val";
 //BA.debugLineNum = 204;BA.debugLine="month = Position + 1";
_vvvvvvvvvvvvvvvvvv4 = (int) (_position+1);
 //BA.debugLineNum = 205;BA.debugLine="DrawDays";
_vvvvvvvvvvvvvvvvv3();
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvv7(int _day,boolean _updatelabel) throws Exception{
 //BA.debugLineNum = 140;BA.debugLine="Private Sub SelectDay(day As Int, UpdateLabel As B";
 //BA.debugLineNum = 141;BA.debugLine="selectedDate = DateUtils.SetDate(year, month, day";
_vvvvvvvvvvvvvvvvvvv6 = _vvvvvvvvvvvvvvv6._setdate(ba,_vvvvvvvvvvvvvvvvvv5,_vvvvvvvvvvvvvvvvvv4,_day);
 //BA.debugLineNum = 142;BA.debugLine="selectedDay = day";
_vvvvvvvvvvvvvvvvvvvv2 = _day;
 //BA.debugLineNum = 143;BA.debugLine="selectedMonth = month";
_vvvvvvvvvvvvvvvvvvvv1 = _vvvvvvvvvvvvvvvvvv4;
 //BA.debugLineNum = 144;BA.debugLine="selectedYear = year";
_vvvvvvvvvvvvvvvvvvv0 = _vvvvvvvvvvvvvvvvvv5;
 //BA.debugLineNum = 145;BA.debugLine="If UpdateLabel Then targetLabel.Text = DateTime.D";
if (_updatelabel) { 
_vvvvvvvvvvvvvvvvvvv7.setText((Object)(__c.DateTime.Date(_vvvvvvvvvvvvvvvvvvv6)));};
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public String  _setdate(long _date,boolean _updatelabel) throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Public Sub SetDate(Date As Long, UpdateLabel As Bo";
 //BA.debugLineNum = 104;BA.debugLine="If waitForAddToActivity Then";
if (_vvvvvvvvvvvvvvvvvvvv6) { 
 //BA.debugLineNum = 105;BA.debugLine="CallSubDelayed3(Me, \"SetDate\", Date, UpdateLabel";
__c.CallSubDelayed3(ba,this,"SetDate",(Object)(_date),(Object)(_updatelabel));
 //BA.debugLineNum = 106;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 108;BA.debugLine="year = DateTime.GetYear(Date)";
_vvvvvvvvvvvvvvvvvv5 = __c.DateTime.GetYear(_date);
 //BA.debugLineNum = 109;BA.debugLine="month = DateTime.GetMonth(Date)";
_vvvvvvvvvvvvvvvvvv4 = __c.DateTime.GetMonth(_date);
 //BA.debugLineNum = 110;BA.debugLine="SelectDay(DateTime.GetDayOfMonth(Date), UpdateLab";
_vvvvvvvvvvvvvvvvv7(__c.DateTime.GetDayOfMonth(_date),_updatelabel);
 //BA.debugLineNum = 111;BA.debugLine="Years.SelectedIndex = year - 1900";
_years.setSelectedIndex((int) (_vvvvvvvvvvvvvvvvvv5-1900));
 //BA.debugLineNum = 112;BA.debugLine="Months.SelectedIndex = month - 1";
_months.setSelectedIndex((int) (_vvvvvvvvvvvvvvvvvv4-1));
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public String  _vvvvvvvvvvvvvvvvv0() throws Exception{
 //BA.debugLineNum = 185;BA.debugLine="Public Sub Show";
 //BA.debugLineNum = 186;BA.debugLine="holder.Visible = True";
_vvvvvvvvvvvvvvvvvv1.setVisible(__c.True);
 //BA.debugLineNum = 187;BA.debugLine="DrawDays";
_vvvvvvvvvvvvvvvvv3();
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public String  _years_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 208;BA.debugLine="Private Sub Years_ItemClick (Position As Int, Valu";
 //BA.debugLineNum = 209;BA.debugLine="year = Value";
_vvvvvvvvvvvvvvvvvv5 = (int)(BA.ObjectToNumber(_value));
 //BA.debugLineNum = 210;BA.debugLine="DrawDays";
_vvvvvvvvvvvvvvvvv3();
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
if (BA.fastSubCompare(sub, "ADDTOACTIVITY"))
	return _addtoactivity((anywheresoftware.b4a.objects.ActivityWrapper) args[0], (anywheresoftware.b4a.objects.LabelWrapper) args[1]);
if (BA.fastSubCompare(sub, "SETDATE"))
	return _setdate(((Number)args[0]).longValue(), (Boolean) args[1]);
return BA.SubDelegator.SubNotFound;
}
}
