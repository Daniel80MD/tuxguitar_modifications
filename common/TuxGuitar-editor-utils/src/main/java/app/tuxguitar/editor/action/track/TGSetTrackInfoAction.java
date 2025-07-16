package app.tuxguitar.editor.action.track;

import app.tuxguitar.action.TGActionContext;
import app.tuxguitar.document.TGDocumentContextAttributes;
import app.tuxguitar.editor.action.TGActionBase;
import app.tuxguitar.song.models.TGColor;
import app.tuxguitar.song.models.TGTrack;
import app.tuxguitar.util.TGContext;

public class TGSetTrackInfoAction extends TGActionBase {

	public static final String NAME = "action.track.set-info";

	public static final String ATTRIBUTE_TRACK_NAME = "name";
	public static final String ATTRIBUTE_TRACK_OFFSET = "offset";
	public static final String ATTRIBUTE_TRACK_COLOR = "color";
	public static final String ATTRIBUTE_TRACK_MAXFRET = "maxFret";

	public TGSetTrackInfoAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(TGActionContext context){
		TGTrack track = ((TGTrack) context.getAttribute(TGDocumentContextAttributes.ATTRIBUTE_TRACK));
		if( track != null ){
			String name = ((String) context.getAttribute(ATTRIBUTE_TRACK_NAME));
			Integer offset = ((Integer) context.getAttribute(ATTRIBUTE_TRACK_OFFSET));
			TGColor color = ((TGColor) context.getAttribute(ATTRIBUTE_TRACK_COLOR));
			Integer maxFret = (Integer) context.getAttribute(ATTRIBUTE_TRACK_MAXFRET);

			if ((maxFret == null) || track.isPercussion()) {
				getSongManager(context).getTrackManager().changeInfo(track, name, color, offset);
			} else {
				getSongManager(context).getTrackManager().changeInfo(track, name, color, offset, maxFret);
			}
		}
	}
}
