package ottplaymedia.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pilihan{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _lebar="";
String _tinggi="";
_lebar = BA.NumberToString((40d / 100 * width));
_tinggi = BA.NumberToString((37d / 100 * height));
views.get("ivlivetv").vw.setWidth((int)(Double.parseDouble(_lebar)));
views.get("ivlivetv").vw.setHeight((int)(Double.parseDouble(_tinggi)));
views.get("ivtvod").vw.setWidth((int)(Double.parseDouble(_lebar)));
views.get("ivtvod").vw.setHeight((int)(Double.parseDouble(_tinggi)));
views.get("ivvod").vw.setWidth((int)(Double.parseDouble(_lebar)));
views.get("ivvod").vw.setHeight((int)(Double.parseDouble(_tinggi)));
views.get("ivmetube").vw.setWidth((int)(Double.parseDouble(_lebar)));
views.get("ivmetube").vw.setHeight((int)(Double.parseDouble(_tinggi)));
views.get("ivlivetv").vw.setLeft((int)((49d / 100 * width)-((views.get("ivlivetv").vw.getWidth())/2d) - (views.get("ivlivetv").vw.getWidth() / 2)));
views.get("ivtvod").vw.setLeft((int)((views.get("ivlivetv").vw.getWidth())+((51d / 100 * width)-((views.get("ivlivetv").vw.getWidth())/2d)) - (views.get("ivtvod").vw.getWidth() / 2)));
views.get("ivvod").vw.setLeft((int)((49d / 100 * width)-((views.get("ivvod").vw.getWidth())/2d) - (views.get("ivvod").vw.getWidth() / 2)));
views.get("ivmetube").vw.setLeft((int)((views.get("ivvod").vw.getWidth())+((51d / 100 * width)-((views.get("ivvod").vw.getWidth())/2d)) - (views.get("ivmetube").vw.getWidth() / 2)));
views.get("ivlivetv").vw.setTop((int)((49.2d / 100 * height)-((views.get("ivlivetv").vw.getHeight())/2d) - (views.get("ivlivetv").vw.getHeight() / 2)));
views.get("ivvod").vw.setTop((int)((views.get("ivlivetv").vw.getHeight())+((50.8d / 100 * height)-((views.get("ivlivetv").vw.getHeight())/2d)) - (views.get("ivvod").vw.getHeight() / 2)));
views.get("ivtvod").vw.setTop((int)((49.2d / 100 * height)-((views.get("ivtvod").vw.getHeight())/2d) - (views.get("ivtvod").vw.getHeight() / 2)));
views.get("ivmetube").vw.setTop((int)((views.get("ivtvod").vw.getHeight())+((50.8d / 100 * height)-((views.get("ivtvod").vw.getHeight())/2d)) - (views.get("ivmetube").vw.getHeight() / 2)));

}
public static void LS_480x320_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("ivvod").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivmetube").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivtvod").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivlivetv").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivlivetv").vw.setLeft((int)((1.8d / 100 * width)));
views.get("ivtvod").vw.setLeft((int)((views.get("ivlivetv").vw.getLeft() + views.get("ivlivetv").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivvod").vw.setLeft((int)((views.get("ivtvod").vw.getLeft() + views.get("ivtvod").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivmetube").vw.setLeft((int)((views.get("ivvod").vw.getLeft() + views.get("ivvod").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivlivetv").vw.setTop((int)((17.5d / 100 * height)));
views.get("ivtvod").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivvod").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivmetube").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivmetube").vw.setHeight((int)((62.5d / 100 * height)));
views.get("ivlivetv").vw.setHeight((int)((views.get("ivmetube").vw.getHeight())));
views.get("ivtvod").vw.setHeight((int)((views.get("ivmetube").vw.getHeight())));
views.get("ivvod").vw.setHeight((int)((views.get("ivmetube").vw.getHeight())));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}