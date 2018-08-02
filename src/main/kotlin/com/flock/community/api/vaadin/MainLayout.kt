package com.flock.community.api.vaadin

import com.flock.community.api.repositories.UserRepository
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.router.RouterLayout
import java.security.Principal


class MainLayout() : Div(), RouterLayout {
    init {
        add(HeaderComponent())
    }
}