package com.chatapplication.domains.files;

import com.chatApplication.domains.FileBase;

public class Audio extends FileBase{
	private String _duration;
	
	
	public Audio(double capacity,byte[] audioByte, String duration) {
		super(capacity,audioByte);
		this._duration = duration;
	}


	@Override
	public FileBase build() {
		return new Audio(super.getCapacity(),super.getFileByte(),_duration);
	}


	public String get_duration() {
		return _duration;
	}


	public void set_duration(String _duration) {
		this._duration = _duration;
	}
	
}
