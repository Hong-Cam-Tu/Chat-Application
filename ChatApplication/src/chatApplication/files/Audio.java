package chatApplication.files;

public class Audio extends File{
	private String _duration;
	
	
	public Audio(double _capacity, String _duration) {
		super(_capacity);
		this._duration = _duration;
	}


	@Override
	public File build() {
		return new Audio(super.getCapacity(),_duration);
	}


	public String get_duration() {
		return _duration;
	}


	public void set_duration(String _duration) {
		this._duration = _duration;
	}
	
}
