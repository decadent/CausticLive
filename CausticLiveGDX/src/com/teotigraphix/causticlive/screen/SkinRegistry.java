////////////////////////////////////////////////////////////////////////////////
// Copyright 2013 Michael Schmalle - Teoti Graphix, LLC
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
// http://www.apache.org/licenses/LICENSE-2.0 
// 
// Unless required by applicable law or agreed to in writing, software 
// distributed under the License is distributed on an "AS IS" BASIS, 
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and 
// limitations under the License
// 
// Author: Michael Schmalle, Principal Architect
// mschmalle at teotigraphix dot com
////////////////////////////////////////////////////////////////////////////////

package com.teotigraphix.causticlive.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.teotigraphix.causticlive.view.main.components.PadButton.PadButtonStyle;
import com.teotigraphix.libgdx.ui.Led.LedStyle;
import com.teotigraphix.libgdx.ui.OverlayButton.OverlayButtonStyle;
import com.teotigraphix.libgdx.ui.SelectButton.SelectButtonStyle;

public class SkinRegistry {

    public static void register(Skin skin) {

        skin.add("eras-12-b", new BitmapFont(Gdx.files.internal("font/Eras-12-B.fnt"), false));

        skin.add("green", new Color(0, 1, 0, 1));
        skin.add("white", new Color(1, 1, 1, 1));
        skin.add("red", new Color(1, 0, 0, 1));
        skin.add("black", new Color(0, 0, 0, 1));

        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("skin/default.fnt"), false);
        skin.add("default-font", bitmapFont);

        LabelStyle labelStyle = new LabelStyle(skin.getFont("default-font"), skin.getColor("white"));
        skin.add("default", labelStyle);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("pad_up");
        textButtonStyle.down = skin.getDrawable("pad_selected");
        textButtonStyle.checked = skin.getDrawable("pad_selected");
        textButtonStyle.font = skin.getFont("default-font");
        textButtonStyle.fontColor = skin.getColor("white");
        skin.add("default", textButtonStyle);

        TextFieldStyle textFieldStyle = new TextFieldStyle();
        textFieldStyle.background = skin.getDrawable("pad_up");
        textFieldStyle.font = skin.getFont("eras-12-b");
        textFieldStyle.fontColor = skin.getColor("white");
        textFieldStyle.cursor = skin.getDrawable("textfield_cursor");
        skin.add("default", textFieldStyle);

        PadButtonStyle padButtonStyle = new PadButtonStyle();
        padButtonStyle.up = skin.getDrawable("pad_up");
        padButtonStyle.down = skin.getDrawable("pad_selected");
        padButtonStyle.checked = skin.getDrawable("pad_selected");

        padButtonStyle.lockOverlay = skin.getDrawable("overlay_unqueued");
        padButtonStyle.playOverlay = skin.getDrawable("overlay_play");
        padButtonStyle.queueOverlay = skin.getDrawable("overlay_queued");

        padButtonStyle.font = skin.getFont("default-font");
        padButtonStyle.fontColor = skin.getColor("white");
        skin.add("default", padButtonStyle);

        ListStyle listStyle = new ListStyle();
        listStyle.selection = skin.getDrawable("list_select_bg");
        listStyle.font = skin.getFont("default-font");
        listStyle.fontColorSelected = skin.getColor("white");
        listStyle.fontColorUnselected = skin.getColor("white");
        skin.add("default", listStyle);

        ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
        scrollPaneStyle.vScroll = skin.getDrawable("list_select_bg");
        scrollPaneStyle.hScroll = skin.getDrawable("list_select_bg");
        scrollPaneStyle.vScrollKnob = skin.getDrawable("list_select_bg");
        scrollPaneStyle.hScrollKnob = skin.getDrawable("list_select_bg");
        scrollPaneStyle.background = skin.getDrawable("list_select_bg");
        skin.add("default", scrollPaneStyle);

        WindowStyle windowStyle = new WindowStyle();
        windowStyle.background = skin.getDrawable("default-rect");
        windowStyle.titleFont = skin.getFont("default-font");
        windowStyle.titleFontColor = skin.getColor("white");
        skin.add("default", windowStyle);

        SelectButtonStyle selectButtonStyle = new SelectButtonStyle();
        selectButtonStyle.up = skin.getDrawable("pad_up");
        selectButtonStyle.down = skin.getDrawable("pad_selected");
        selectButtonStyle.checked = skin.getDrawable("pad_selected");
        selectButtonStyle.font = skin.getFont("default-font");
        selectButtonStyle.fontColor = skin.getColor("white");
        selectButtonStyle.checkedFontColor = skin.getColor("red");
        selectButtonStyle.disabledFontColor = skin.getColor("white");
        skin.add("default", selectButtonStyle);

        createOverlayButtonStyle(skin, "default", "pad_up", "pad_selected", "pad_selected");

        //
        //        PadButtonStyle padButtonStyle = new PadButtonStyle();
        //        padButtonStyle.up = skin.getDrawable("pad_up");
        //        padButtonStyle.down = skin.getDrawable("pad_selected");
        //        padButtonStyle.checked = skin.getDrawable("pad_selected");
        //        padButtonStyle.font = skin.getFont("default-font");
        //        padButtonStyle.fontColor = skin.getColor("white");
        //
        //        padButtonStyle.queueOverlay = skin.getDrawable("overlay_queued");
        //        padButtonStyle.playOverlay = skin.getDrawable("overlay_play");
        //        padButtonStyle.lockOverlay = skin.getDrawable("pad_selected");
        //        skin.add("default", padButtonStyle);

        LedStyle ledRedStyle = new LedStyle();
        ledRedStyle.font = skin.getFont("default-font");
        ledRedStyle.fontColor = skin.getColor("white");
        ledRedStyle.on = skin.getDrawable("led_on_red");
        ledRedStyle.off = skin.getDrawable("led_off_red");
        skin.add("led-red", ledRedStyle);

        LedStyle ledGreenStyle = new LedStyle();
        ledGreenStyle.font = skin.getFont("default-font");
        ledGreenStyle.fontColor = skin.getColor("white");
        ledGreenStyle.on = skin.getDrawable("led_on_green");
        ledGreenStyle.off = skin.getDrawable("led_off_green");
        skin.add("led-green", ledGreenStyle);

        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.background = skin.getDrawable("slider_background");
        sliderStyle.knob = skin.getDrawable("slider_knob");
        skin.add("default-vertical", sliderStyle);

        SelectBoxStyle selectBoxStyle = new SelectBoxStyle();
        selectBoxStyle.background = skin.getDrawable("pad_up");
        selectBoxStyle.font = skin.getFont("default-font");
        selectBoxStyle.fontColor = skin.getColor("white");
        selectBoxStyle.scrollStyle = scrollPaneStyle;
        selectBoxStyle.listStyle = listStyle;
        skin.add("default", selectBoxStyle);
    }

    public static OverlayButtonStyle createOverlayButtonStyle(Skin skin, String name, String up,
            String selected, String disabled) {
        OverlayButtonStyle style = new OverlayButtonStyle();
        style.up = skin.getDrawable(up);
        style.down = skin.getDrawable(selected);
        style.checked = skin.getDrawable(selected);
        style.disabled = skin.getDrawable(disabled);
        style.font = skin.getFont("default-font");
        style.fontColor = skin.getColor("white");
        style.progressOverlay = skin.getDrawable("pad_selected");
        skin.add(name, style);
        return style;
    }
}
