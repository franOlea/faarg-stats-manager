package org.faarg.stats.manager.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.faarg.stats.manager.model.team.Team;
import org.faarg.stats.manager.model.team.player.Position;
import org.faarg.stats.manager.service.repository.TeamRepository;
import org.faarg.stats.manager.ui.editors.TeamEditor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

@SpringUI(path = "/teams/crud")
@Theme("valo")
public class TeamCRUDUI extends UI {

    private final TeamRepository repository;
    private final TeamEditor editor;
    private final Grid grid;
    private final Button addNewBtn;

    @Autowired
    public TeamCRUDUI(final TeamRepository repository,
                      final TeamEditor editor) {
        this.repository = repository;
        this.editor = editor;
        this.grid = new Grid();
        this.addNewBtn = new Button("Equipo nuevo", FontAwesome.PLUS);
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
        grid.setColumns("id", "name", "coachName", "websiteUrl", "creationYear");

        grid.getColumn("id").setHeaderCaption("ID");
        grid.getColumn("name").setHeaderCaption("NOMBRE");
        grid.getColumn("coachName").setHeaderCaption("COACH");
        grid.getColumn("websiteUrl").setHeaderCaption("PAGINA WEB");
        grid.getColumn("creationYear").setHeaderCaption("CREACION");

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editTeam((Team) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editTeam(new Team("", "","", new Date(0))));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listPositions();
        });

        // Initialize listing
        listPositions();
    }

    private void listPositions() {
        grid.setContainerDataSource(new BeanItemContainer(Team.class, repository.findAll()));
    }
}
