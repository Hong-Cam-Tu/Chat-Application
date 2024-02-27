package chatApplication.files;

public class Image extends File {
    private double _resolution;
  
    public Image(double _capacity, double _resolution) {
        super(_capacity);
        this._resolution = _resolution;
    }
    
    @Override
    public File build() {
        return new Image(super.getCapacity(), _resolution);
    }

    public double get_resolution() {
        return _resolution;
    }

    public void set_resolution(double _resolution) {
        this._resolution = _resolution;
    }
    
}
