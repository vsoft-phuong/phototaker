/*
 * Copyright (c) 2016 Piruin Panichphol
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.piruin.phototaker;

import android.app.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_FIRST_USER;
import static me.piruin.phototaker.PhotoTaker.CROP_IMAGE;
import static me.piruin.phototaker.PhotoTaker.IMAGE_CAPTURE;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PhotoTakerTest {

  private static final int OTHER_ACTION = 1;

  @Mock PhotoTakerListener listener;
  @Mock Activity activity;

  @Test public void onResultOfCropImageCancel() throws Exception {
    PhotoTaker taker = new PhotoTaker(activity, new PhotoSize(400, 400));
    taker.setListener(listener);

    taker.onActivityResult(CROP_IMAGE, RESULT_CANCELED, null);

    verify(listener).onCancel(CROP_IMAGE);
  }


  @Test public void onResultOfCaptureCancel() throws Exception {
    PhotoTaker taker = new PhotoTaker(activity, new PhotoSize(400, 400));
    taker.setListener(listener);

    taker.onActivityResult(IMAGE_CAPTURE, RESULT_CANCELED, null);

    verify(listener).onCancel(IMAGE_CAPTURE);
  }

  @Test public void onResultOfOtherActionCancel() throws Exception {
    PhotoTaker taker = new PhotoTaker(activity, new PhotoSize(400, 400));
    taker.setListener(listener);

    taker.onActivityResult(OTHER_ACTION, RESULT_CANCELED, null);

    verify(listener, never()).onCancel(anyInt());
  }

  @Test public void onResultOfCaptureNotOk() throws Exception {
    PhotoTaker taker = new PhotoTaker(activity, new PhotoSize(400, 400));
    taker.setListener(listener);

    taker.onActivityResult(IMAGE_CAPTURE, RESULT_FIRST_USER, null);

    verify(listener).onError(IMAGE_CAPTURE);
  }

  @Test public void onResultOfCropNotOk() throws Exception {
    PhotoTaker taker = new PhotoTaker(activity, new PhotoSize(400, 400));
    taker.setListener(listener);

    taker.onActivityResult(CROP_IMAGE, RESULT_FIRST_USER, null);

    verify(listener).onError(CROP_IMAGE);
  }

  @Test public void onResultOfOtherNotOk() throws Exception {
    PhotoTaker taker = new PhotoTaker(activity, new PhotoSize(400, 400));
    taker.setListener(listener);

    taker.onActivityResult(OTHER_ACTION, RESULT_FIRST_USER, null);

    verify(listener, never()).onError(anyInt());
  }
}
