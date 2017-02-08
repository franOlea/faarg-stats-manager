package org.faarg.stats.manager.ui.editors;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.faarg.stats.manager.model.team.Team;
import org.faarg.stats.manager.service.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class TeamEditor extends VerticalLayout {

    private final TeamRepository repository;

    private Team team;

    private TextField name = new TextField("Nombre");
    private TextField coachName = new TextField("Nombre del coach");
    private TextField websiteUrl = new TextField("Pagina web");
    private DateField creationYear = new DateField();

    private Button save = new Button("Guardar", FontAwesome.SAVE);
    private Button cancel = new Button("Cancelar");
    private Button delete = new Button("Eliminar", FontAwesome.TRASH_O);
    private CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public TeamEditor(final TeamRepository teamRepository) {
        this.repository = teamRepository;

        creationYear.setDateFormat("yyyy");

        addComponents(
                name,
                coachName,
                websiteUrl,
                creationYear,
                actions
        );

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> repository.save(team));
        delete.addClickListener(e -> repository.delete(team));
        cancel.addClickListener(e -> editTeam(team));
        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editTeam(Team editedTeam) {
        final boolean persisted = editedTeam.getId() != null;
        if(persisted) {
            team = repository.findOne(editedTeam.getId());
        } else {
            team = editedTeam;
        }

        cancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(team, this);
        setVisible(true);

        save.focus();
        name.selectAll();
    }

    public void setChangeHandler(PositionEditor.ChangeHandler handler) {
        save.addClickListener(e -> handler.onChange());
        delete.addClickListener(e -> handler.onChange());
    }

}
