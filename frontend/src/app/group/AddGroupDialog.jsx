import { Dialog, DialogContent, DialogTitle } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import TextField from "@material-ui/core/TextField";
import React, { useState } from 'react';


const AddGroupDialog = ({ open, onSubmit, onClose }) => {
    const [name, setName] = useState("");

    return (

        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Dodaj grupę</DialogTitle>
            <DialogContent>
                <TextField
                    autoFocus
                    margin="dense"
                    id="add-grup-name"
                    label="Nazwa grupy"
                    type="text"
                    fullWidth
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
            </DialogContent>

            <DialogActions>
                <Button onClick={() => {
                    onSubmit(name);
                    setName("");
                    onClose();
                }} color="primary" variant="contained">
                    Dodaj
                </Button>
                <Button onClick={onClose} color="primary" variant="text">
                    Cofnij
                </Button>
            </DialogActions>
        </Dialog>

    );
};


export default AddGroupDialog;
