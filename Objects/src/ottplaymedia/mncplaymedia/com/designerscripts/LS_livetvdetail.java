package ottplaymedia.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_livetvdetail{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _tinggi="";
_tinggi = BA.NumberToString((11d / 100 * height));
views.get("ivepgdet2").vw.setWidth((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet2").vw.setHeight((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet2").vw.setTop((int)((2.6d / 100 * height)));
views.get("ivepgdet2").vw.setLeft((int)((91d / 100 * width) - (views.get("ivepgdet2").vw.getWidth())));
views.get("ivepgdet").vw.setWidth((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet").vw.setLeft((int)((views.get("ivepgdet2").vw.getLeft() + views.get("ivepgdet2").vw.getWidth())+(1d / 100 * width)));
views.get("ivepgdet").vw.setHeight((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet").vw.setTop((int)((views.get("ivepgdet2").vw.getTop())));
views.get("ivthumdet").vw.setLeft((int)(0d));
views.get("ivthumdet").vw.setWidth((int)(Double.parseDouble(_tinggi)+(Double.parseDouble(_tinggi)/1.5d) - (0d)));
views.get("ivthumdet").vw.setTop((int)((0.5d / 100 * height)));
views.get("ivthumdet").vw.setHeight((int)(Double.parseDouble(_tinggi)-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("lblepgdet").vw.setLeft((int)((views.get("ivthumdet").vw.getWidth())));
views.get("lblepgdet").vw.setWidth((int)((views.get("ivepgdet2").vw.getLeft())-(1d / 100 * width) - ((views.get("ivthumdet").vw.getWidth()))));
views.get("lblepgdet").vw.setTop((int)((views.get("ivthumdet").vw.getTop())));
views.get("lblepgdet").vw.setHeight((int)((views.get("ivthumdet").vw.getTop() + views.get("ivthumdet").vw.getHeight()) - ((views.get("ivthumdet").vw.getTop()))));

}
public static void LS_480x320_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _tinggi="";
_tinggi = BA.NumberToString((15d / 100 * height));
views.get("ivepgdet2").vw.setWidth((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet2").vw.setHeight((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet2").vw.setTop((int)((3.6d / 100 * height)));
views.get("ivepgdet2").vw.setLeft((int)((93d / 100 * width) - (views.get("ivepgdet2").vw.getWidth())));
views.get("ivepgdet").vw.setWidth((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet").vw.setLeft((int)((views.get("ivepgdet2").vw.getLeft() + views.get("ivepgdet2").vw.getWidth())+(1d / 100 * width)));
views.get("ivepgdet").vw.setHeight((int)(Double.parseDouble(_tinggi)/2.1d));
views.get("ivepgdet").vw.setTop((int)((views.get("ivepgdet2").vw.getTop())));
views.get("ivthumdet").vw.setLeft((int)(0d));
views.get("ivthumdet").vw.setWidth((int)(Double.parseDouble(_tinggi)+(Double.parseDouble(_tinggi)/1.5d) - (0d)));
views.get("ivthumdet").vw.setTop((int)((0.5d / 100 * height)));
views.get("ivthumdet").vw.setHeight((int)(Double.parseDouble(_tinggi)-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("lblepgdet").vw.setLeft((int)((views.get("ivthumdet").vw.getWidth())));
views.get("lblepgdet").vw.setWidth((int)((views.get("ivepgdet2").vw.getLeft())-(1d / 100 * width) - ((views.get("ivthumdet").vw.getWidth()))));
views.get("lblepgdet").vw.setTop((int)((views.get("ivthumdet").vw.getTop())));
views.get("lblepgdet").vw.setHeight((int)((views.get("ivthumdet").vw.getTop() + views.get("ivthumdet").vw.getHeight()) - ((views.get("ivthumdet").vw.getTop()))));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}