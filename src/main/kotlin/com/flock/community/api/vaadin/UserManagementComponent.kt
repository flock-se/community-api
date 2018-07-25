package com.flock.community.api.vaadin

import com.flock.community.api.authorities.Authority
import com.flock.community.api.authorities.MemberAuthorities
import com.flock.community.api.authorities.UserAuthorities
import com.flock.community.api.model.User
import com.flock.community.api.repositories.UserRepository
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.data.provider.DataProvider
import com.vaadin.flow.router.Route
import org.springframework.data.domain.PageRequest

@Route("ui/users", layout = MainLayout::class)
open class UserManagementComponent(private val userRepository: UserRepository) : Div() {

    init {

        val userGrid = userGrid()
        userGrid.height = "100vh"
        userGrid.asSingleSelect().addValueChangeListener { event ->
            val user = event.value.let { user ->
                val userForm = authorityForm(user)
                val formDialog = formDialog(
                        userForm,
                        {
                            val user = user.copy(
                                    authorities = userForm.selectedItems
                                            .map({ it.toName() })
                                            .toList()
                            )
                            userRepository.save(user)
                            userGrid.dataProvider.refreshAll()
                        }

                )
                formDialog.open()
            }
        }

        this.add(userGrid)

    }

    fun formDialog(
            form: Component,
            saveEvent: (ClickEvent<Button>) -> Unit = {},
            cancelEvent: (ClickEvent<Button>) -> Unit = {}
    ): Dialog {

        val dialog = Dialog()
        dialog.setWidth("400px")
        dialog.setHeight("300px")

        val saveButton = Button("Save")
        saveButton.addClickListener({
            saveEvent.invoke(it)
            dialog.close()
        })

        val buttons = HorizontalLayout()
        buttons.setVerticalComponentAlignment(FlexComponent.Alignment.END, saveButton)
        buttons.add(saveButton)

        val content = VerticalLayout(form, buttons)
        content.setHeight("100%");
        content.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);

        dialog.add(content)

        return dialog
    }

    private fun userGrid(): Grid<User> {
        val grid = Grid(User::class.java)
        grid.setColumns("name", "email")
        grid.addColumn({ it.authorities.size }).setHeader("Authorities")
        grid.dataProvider = userRepository.toDataProvider(grid.pageSize)
        return grid
    }

    private fun authorityForm(user: User): Grid<Authority> {

        val grid = Grid(Authority::class.java)

        val items: List<Authority> = arrayOf(
                MemberAuthorities.values() as Array<Authority>,
                UserAuthorities.values() as Array<Authority>
        ).flatten()

        grid.setItems(items)



        grid.select(items[0])
        grid.addColumn(Authority::toName).setHeader("Value")

        grid.setSelectionMode(Grid.SelectionMode.MULTI)
        grid.asMultiSelect()

        items
                .filter({ user.authorities.contains(it.toName()) })
                .forEach({
                    grid.asMultiSelect().select(it)
                })

        grid.height = "100%"

        return grid
    }


    fun UserRepository.toDataProvider(pageSize: Int): DataProvider<User, Void> {
        return DataProvider.fromCallbacks<User>(
                { query ->
                    System.out.println(query.limit)
                    System.out.println(query.offset)
                    val page = (query.offset / query.limit)
                    val pageable = PageRequest.of(page, pageSize)
                    userRepository.findAll(pageable).toList().stream()
                }
        ) { query -> userRepository.count().toInt() }
    }
}