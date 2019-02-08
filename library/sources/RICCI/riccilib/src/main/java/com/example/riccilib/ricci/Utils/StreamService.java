package com.example.riccilib.ricci.Utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by brenocruz on 1/27/17.
 */

public interface StreamService {

    void setContext(Context context);

    void setData(Intent data);

    String getFileExtension();

    byte[] loadData();

}
