package org.faarg.stats.manager.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.faarg.stats.manager.model.team.player.Player;
import org.faarg.stats.manager.service.repository.PlayerRepository;
import org.faarg.stats.manager.ui.editors.PlayerEditor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

@SpringUI(path = "/players/crud")
@Theme("valo")
public class PlayerCRUDUI extends UI {

    private final PlayerRepository repository;
    private final PlayerEditor editor;
    private final Grid grid;
    private final Button addNewBtn;

    @Autowired
    public PlayerCRUDUI(final PlayerRepository playerRepository,
                        final PlayerEditor playerEditor) {
        this.repository = playerRepository;
        this.editor = playerEditor;
        this.grid = new Grid();
        this.addNewBtn = new Button("Jugador nuevo", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {
        // build layout
        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        setContent(mainLayout);

        // Configure layouts and components
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth(600, Unit.PIXELS);
        grid.setColumns(
                "id",
                "firstName",
                "lastName",
                "birthDate",
                "joinedYear",
                "weight",
                "height",
                "profileImageUrl"
        );

        grid.getColumn("id").setHeaderCaption("ID");
        grid.getColumn("firstName").setHeaderCaption("NOMBRE");
        grid.getColumn("lastName").setHeaderCaption("APELLIDO");
        grid.getColumn("birthDate").setHeaderCaption("NACIMIENTO");
        grid.getColumn("joinedYear").setHeaderCaption("INGRESO");
        grid.getColumn("weight").setHeaderCaption("PESO");
        grid.getColumn("height").setHeaderCaption("ALTURA");
        grid.getColumn("profileImageUrl").setHeaderCaption("URL IMAGEN");

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editPlayer((Player) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e ->  {
            editor.editPlayer(
                    new Player(
                            "",
                            "",
                            new Date(0),
                            new Date(0),
                            0,
                            0,
                            "")
            );
        });

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listPositions();
        });

        // Initialize listing
        listPositions();
    }

    private void listPositions() {
        grid.setContainerDataSource(new BeanItemContainer(Player.class, repository.findAll()));
    }

}
