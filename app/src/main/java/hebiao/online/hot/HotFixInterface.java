package hebiao.online.hot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HotFixInterface {
    String clazz();  /// 被修复的类
    String method(); /// 被修复的方法
}
