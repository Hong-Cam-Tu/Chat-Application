package com.chatapplication.domains.files;

import com.chatapplication.domains.FileBase;

public class Image extends FileBase {
    private double _resolution;
  
    public Image(double capacity,byte[] imageByte, double resolution) {
        super(capacity,imageByte);
        this._resolution = resolution;
    }
    
    @Override
    public FileBase build() {
        return new Image(super.getCapacity(),super.getFileByte(), _resolution);
    }

    public double get_resolution() {
        return _resolution;
    }

    public void set_resolution(double _resolution) {
        this._resolution = _resolution;
    }
    
}
