package cafe.adriel.androidaudiorecorder.example;

/**
 * Created by devashish on 11-08-2016.
 */
public class IncompatibleRatioException extends RuntimeException {

    private static final long serialVersionUID = 234608108593115395L;

    public IncompatibleRatioException() {
        super("Can't perform Ken Burns effect on rects with distinct aspect ratios!");
    }
}