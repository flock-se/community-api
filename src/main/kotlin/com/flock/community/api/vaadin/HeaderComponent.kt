package com.flock.community.api.vaadin

import com.vaadin.flow.component.html.Header
import com.vaadin.flow.component.tabs.Tab
import com.vaadin.flow.component.tabs.Tabs
import java.security.Principal


class HeaderComponent : Header() {
    init {

        val tab1 = Tab("Members")
        val tab2 = Tab("Users")

        val tabs = Tabs(tab1, tab2)

        tabs.addSelectedChangeListener { event ->
            System.out.println("Me: ")
        }

        add(tabs)
    }
}