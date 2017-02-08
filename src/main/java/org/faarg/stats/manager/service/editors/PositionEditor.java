package org.faarg.stats.manager.service.editors;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.faarg.stats.manager.model.league.team.player.Position;
import org.faarg.stats.manager.service.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class PositionEditor extends VerticalLayout {

    private final PositionRepository repository;

    private Position position;

    TextField name = new TextField("Nombre");

    Button save = new Button("Guardar", FontAwesome.SAVE);
    Button cancel = new Button("Cancelar");
    Button delete = new Button("Eliminar", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public PositionEditor(PositionRepository repository) {
        this.repository = repository;

        addComponents(name, actions);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(position));
        delete.addClickListener(e -> repository.delete(position));
        cancel.addClickListener(e -> editPosition(position));
        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editPosition(Position editedPosition) {
        final boolean persisted = editedPosition.getId() != null;
        if(persisted) {
            position = repository.findOne(editedPosition.getId());
        } else {
            position = editedPosition;
        }

        cancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(position, this);
        setVisible(true);

        save.focus();
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler handler) {
        save.addClickListener(e -> handler.onChange());
        delete.addClickListener(e -> handler.onChange());
    }

}
