package net.szvoc.callcenter.sip.core;

import lombok.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Sdp {
    private final static String FIELD_VERSION = "v"; //协议版本
    private final static String FIELD_BANDWIDTH = "b"; //带宽信息
    private final static String FIELD_ORIGIN = "o"; //会话主人和标志
    private final static String FIELD_ZONE = "z"; //时区
    private final static String FIELD_SESSION_NAME = "s"; //会话名称
    private final static String FIELD_KEY = "k"; //密钥
    private final static String FIELD_INFO = "i"; //会话信息
    private final static String FIELD_ATTRIBUTE = "a"; //属性行
    private final static String FIELD_URI = "u"; //含有会话描述的url
    private final static String FIELD_TIME = "t"; //会话激活时间
    private final static String FIELD_EMAIL = "e"; //获取会话信息的email地址
    private final static String FIELD_REPEAT = "r"; //会话重复次数
    private final static String FIELD_PHONE = "p"; //获取会话信息的电话号码
    private final static String FIELD_CONNECTION = "c"; //连接信息
    private final static String FIELD_MEDIA = "m"; //媒体行

    private int version;
    private String bandWidth;
    private Origin origin;
    private String zone;
    private String sessionName;
    private String key;
    private String info;
    private String attribute;
    private String uri;
    private String time;
    private String email;
    private int repeat;
    private String phone;
    private String connection;
    private List<Media> mediaList = new ArrayList<>();
    private List<Field> unknownFields = new ArrayList<>();

    private void parse(String text) {
        var sdp = new Sdp();
        Media media = null;
        for (var line : text.split("\r\n")) {
            var field = Field.parse(line);
            switch (field.getName()) {
                case FIELD_VERSION:
                    sdp.setVersion(Integer.parseInt(field.getValue()));
                    break;
                case FIELD_BANDWIDTH:
                    sdp.setBandWidth(field.getValue());
                    break;
                case FIELD_ORIGIN:
                    sdp.setOrigin(Origin.parse(field.getValue()));
                    break;
                case FIELD_ZONE:
                    sdp.setZone(field.getValue());
                    break;
                case FIELD_SESSION_NAME:
                    sdp.setSessionName(field.getValue());
                    break;
                case FIELD_KEY:
                    sdp.setKey(field.getValue());
                    break;
                case FIELD_INFO:
                    sdp.setInfo(field.getValue());
                    break;
                case FIELD_ATTRIBUTE:
                    if (media == null) {
                        sdp.setAttribute(field.getValue());
                    } else {
                        media.attr(field.getValue());
                    }
                    break;
                case FIELD_URI:
                    sdp.setUri(field.getValue());
                    break;
                case FIELD_TIME:
                    sdp.setTime(field.getValue());
                    break;
                case FIELD_EMAIL:
                    sdp.setEmail(field.getValue());
                    break;
                case FIELD_REPEAT:
                    sdp.setRepeat(Integer.parseInt(field.getValue()));
                    break;
                case FIELD_PHONE:
                    sdp.setPhone(field.getValue());
                    break;
                case FIELD_CONNECTION:
                    sdp.setConnection(field.getValue());
                    break;
                case FIELD_MEDIA:
                    media = Media.parse(field.getValue());
                    sdp.mediaList.add(media);
                    break;
                default:
                    unknownFields.add(field);
                    break;
            }
        }
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class Origin {
        private String name;
        private String addressFamily;
        private String host;

        public static Origin parse(String text) {
            //TODO not implement
            throw new NotImplementedException();
        }
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class Media {
        private String mediaType; // 媒体类型
        private int port; // 端口号
        private String protocol; //传输协议
        private String codec; // 解码器
        private int sample; // 采样率

        public static Media parse(String text) {
            //TODO not implement
            throw new NotImplementedException();
        }

        public void attr(String text) {
            //TODO not implement
            throw new NotImplementedException();
        }
    }
}
