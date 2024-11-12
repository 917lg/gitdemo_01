package cn.jbolt.model;

import com.jfinal.plugin.activerecord.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/5 11:15
 */

public class books extends Model<books> {
    public static final books dao = new books();
}
