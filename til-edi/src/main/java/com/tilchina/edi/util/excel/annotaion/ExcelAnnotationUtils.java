package com.tilchina.edi.util.excel.annotaion;

import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class ExcelAnnotationUtils {
    /**
     * 添加到 annotationList
     */
    public static void addAnnotation(List<Object[]> annotationList, ExcelField ef, Object object, ExcelField.Type type, String... groups) {
        if (ef != null && (ef.type() == ExcelField.Type.ALL || ef.type() == type)) {
            if (groups != null && groups.length > 0) {
                boolean inGroup = false;
                for (String g : groups) {
                    if (inGroup) {
                        break;
                    }
                    for (String efg : ef.groups()) {
                        if (StringUtils.equals(g, efg)) {
                            inGroup = true;
                            annotationList.add(new Object[]{ef, object});
                            break;
                        }
                    }
                }
            } else {
                annotationList.add(new Object[]{ef, object});
            }
        }
    }
}
