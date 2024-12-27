package com.hangu.tool.mybatis.secret.util;

import com.hangu.tool.mybatis.secret.bo.FieldEncryptSnapshotBo;
import java.util.List;

/**
 * @author wuzhenhong
 * @date 2024/12/27 9:48
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<List<FieldEncryptSnapshotBo>>
        THREAD_LOCAL = new ThreadLocal<>();

    public static void set(List<FieldEncryptSnapshotBo> list) {
        THREAD_LOCAL.set(list);
    }

    public static List<FieldEncryptSnapshotBo> get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
