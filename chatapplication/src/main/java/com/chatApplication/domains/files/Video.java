package com.chatapplication.domains.files;

import com.chatapplication.domains.FileBase;

public class Video extends FileBase {    
    private String _resolution;
    private int _durationInSeconds;
    
    public Video(double _capacity,byte[] videoByte, String _resolution, int _durationInSeconds) {
        super(_capacity,videoByte);
        this._resolution = _resolution;
        this._durationInSeconds = _durationInSeconds;
    }

    public String getResolution() {
        return _resolution;
    }

    public void setResolution(String resolution) {
        _resolution = resolution;
    }

    public int getDurationInSeconds() {
        return _durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        _durationInSeconds = durationInSeconds;
    }

    @Override
    public FileBase build(){
        return new Video(super.getCapacity(),super.getFileByte(),_resolution,_durationInSeconds);
    }
}
