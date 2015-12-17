package medialibrary.entity;

import medialibrary.entity.quality.AudioType;
import medialibrary.entity.quality.VideoQuality;

/**
 * Created by Hugefish on 13.12.2015.
 */
public class FilmFile extends MediaFile {
    private VideoQuality quality;
    private AudioType audioType;
    private int videoWightSize;
    private int videoHeighSize;
    private int audioBitrate;
    private int videoBitrate;

    public FilmFile(int year, long size, String name, String file, VideoQuality quality, int videoBitrate, int audioBitrate, int videoWightSize, int videoHeighSize, AudioType audioType) {
        super(year, size, name, file);
        this.quality = quality;
        this.videoBitrate = videoBitrate;
        this.audioBitrate = audioBitrate;
        this.videoWightSize = videoWightSize;
        this.videoHeighSize = videoHeighSize;
        this.audioType = audioType;
    }

    public FilmFile(){
        this.quality = VideoQuality.NONE;
        this.videoBitrate = 0;
        this.audioBitrate = 0;
        this.videoWightSize = 0;
        this.videoHeighSize = 0;
        this.audioType = AudioType.NONE;
    }

    public VideoQuality getQuality() {
        return quality;
    }
    public void setQuality(VideoQuality quality) {
        this.quality = quality;
    }
    public AudioType getAudioType() {
        return audioType;
    }
    public void setAudioType(AudioType audioType) {
        this.audioType = audioType;
    }
    public int getVideoWightSize() {
        return videoWightSize;
    }
    public void setVideoWightSize(int videoWightSize) {
        this.videoWightSize = videoWightSize;
    }
    public int getVideoHeighSize() {
        return videoHeighSize;
    }
    public void setVideoHeighSize(int videoHeighSize) {
        this.videoHeighSize = videoHeighSize;
    }
    public int getAudioBitrate() {
        return audioBitrate;
    }
    public void setAudioBitrate(int audioBitrate) {
        this.audioBitrate = audioBitrate;
    }
    public int getVideoBitrate() {
        return videoBitrate;
    }
    public void setVideoBitrate(int videoBitrate) {
        this.videoBitrate = videoBitrate;
    }

}
