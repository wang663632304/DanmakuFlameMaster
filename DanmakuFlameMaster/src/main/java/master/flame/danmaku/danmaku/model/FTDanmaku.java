/*
 * Copyright (C) 2013 Chen Hui <calmer91@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package master.flame.danmaku.danmaku.model;

/**
 * 顶部固定弹幕
 */
public class FTDanmaku extends BaseDanmaku {

    private float x = 0;

    protected float y = -1;

    public FTDanmaku(long duration) {
        this.duration = duration;
    }

    @Override
    public void layout(IDisplayer displayer, float x, float y) {
        if (mTimer != null) {
            long deltaDuration = mTimer.currMillisecond - time;
            if (deltaDuration >= 0 && deltaDuration <= duration) {
                if (this.visibility == INVISIBLE) {
                    this.x = getLeft(displayer);
                    this.y = y;
                    this.visibility = VISIBLE;
                }
            } else if (deltaDuration > duration) {
                this.visibility = INVISIBLE;
            } else if (deltaDuration < 0) {
                this.visibility = INVISIBLE;
            }
        }

    }

    protected float getLeft(IDisplayer displayer) {
        float left = (displayer.getWidth() - paintWidth) / 2;
        return left;
    }

    @Override
    public float[] getRectAtTime(IDisplayer displayer, long time) {
        if (!isMeasured())
            return null;
        float left = getLeft(displayer);
        float[] rect = new float[] {
                left, y, left + paintWidth, y + paintHeight
        };
        return rect;
    }

    @Override
    public float getLeft() {
        return x;
    }

    @Override
    public float getTop() {
        return y;
    }

    @Override
    public float getRight() {
        return x + paintWidth;
    }

    @Override
    public float getBottom() {
        return y + paintHeight;
    }

    @Override
    public int getType() {
        return TYPE_FIX_TOP;
    }
}
