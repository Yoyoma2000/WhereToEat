package persistence;

import org.json.JSONObject;

// This interface represents the indicator of model classes that is involved with saving and loading
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
