package com.flock.community.api.vaadin

import com.vaadin.flow.component.html.Div
import com.vaadin.flow.router.RouterLayout


class MainLayout : Div(), RouterLayout {
    init {
        add(HeaderComponent())
    }
}