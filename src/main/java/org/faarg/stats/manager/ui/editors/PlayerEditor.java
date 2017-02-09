package org.faarg.stats.manager.ui.editors;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.faarg.stats.manager.model.team.player.Player;
import org.faarg.stats.manager.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class PlayerEditor extends VerticalLayout {

    private final PlayerRepository repository;

    private Player player;

    private TextField firstName = new TextField("Nombre");
    private TextField lastName = new TextField("Apellido");
    private DateField birthDate = new DateField("Nacimiento");
    private DateField joinedLeague = new DateField("Ingreso en la liga");
    private TextField weight = new TextField("Peso (Kg)");
    private TextField height = new TextField("Altura (Mt)");

    private Button save = new Button("Guardar", FontAwesome.SAVE);
    private Button cancel = new Button("Cancelar");
    private Button delete = new Button("Eliminar", FontAwesome.TRASH_O);
    private CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public PlayerEditor(final PlayerRepository playerRepository) {
        this.repository = playerRepository;

        joinedLeague.setDateFormat("yyyy");

        addComponents(
                firstName,
                lastName,
                birthDate,
                weight,
                height,
                actions
        );

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> repository.save(player));
        delete.addClickListener(e -> repository.delete(player));
        cancel.addClickListener(e -> editPlayer(player));
        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editPlayer(Player editedPlayer) {
        final boolean persisted = editedPlayer.getId() != null;
        if(persisted) {
            player = repository.findOne(editedPlayer.getId());
        } else {
            player = editedPlayer;
        }

        cancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(player, this);
        setVisible(true);

        save.focus();
    }

    public void setChangeHandler(PositionEditor.ChangeHandler handler) {
        save.addClickListener(e -> handler.onChange());
        delete.addClickListener(e -> handler.onChange());
    }

}
