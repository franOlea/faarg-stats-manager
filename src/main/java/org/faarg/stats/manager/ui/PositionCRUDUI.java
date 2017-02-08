package org.faarg.stats.manager.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.faarg.stats.manager.model.team.player.Position;
import org.faarg.stats.manager.ui.editors.PositionEditor;
import org.faarg.stats.manager.service.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/positions/crud")
@Theme("valo")
public class PositionCRUDUI extends UI {

    private PositionRepository repository;
    private PositionEditor editor;
    private Grid grid;

    private final Button addNewBtn;

    @Autowired
    public PositionCRUDUI(PositionRepository repository, PositionEditor editor) {
        this.repository = repository;
        this.editor = editor;
        this.grid = new Grid();
        this.addNewBtn = new Button("Posicion nueva", FontAwesome.PLUS);
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
        grid.setColumns("id", "name");

        grid.getColumn("id").setHeaderCaption("ID");
        grid.getColumn("name").setHeaderCaption("NOMBRE");

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editPosition((Position) grid.getSelectedRow());
            }
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editPosition(new Position("")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listPositions();
        });

        // Initialize listing
        listPositions();
    }

    private void listPositions() {
        grid.setContainerDataSource(new BeanItemContainer(Position.class, repository.findAll()));
    }
}
