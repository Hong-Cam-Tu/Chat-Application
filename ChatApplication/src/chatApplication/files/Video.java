package chatApplication.files;
public class Video extends File {    
    private String _resolution;
    private int _durationInSeconds;
    
    public Video(double _capacity, String _resolution, int _durationInSeconds) {
        super(_capacity);
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
    public File build(){
        return new Video(super.getCapacity(),_resolution,_durationInSeconds);
    }
}
