import { Dialog, DialogContent, DialogTitle } from "@material-ui/core";
import Button from "@material-ui/core/Button/index";
import DialogActions from "@material-ui/core/DialogActions/index";
import TextField from "@material-ui/core/TextField/index";
import * as PropTypes from "prop-types";
import React, { Component } from 'react';
import css from './dialogs.module.scss'


const defaultPhotoUrl = "https://3ie87c2dond928rt2e2zzo8o-wpengine.netdna-ssl.com/wp-content/themes/gonzo/images/no-image-featured-image.png";

class AddGroupDialog extends Component {

    state = {
        name: "",
        photoUrl: "",
        formErrors: {
            name: "",
        }
    };

    formValid = () => {
        let valid = true;
        const { formErrors, photoUrl, ...rest } = this.state;

        Object.values(formErrors).forEach(val => {
            val && val.length > 0 && (valid = false);
        });

        Object.values(rest).forEach(val => {
            !val && (valid = false);
        });


        return valid;
    };

    handleChange = (name) => (event) => {
        event.preventDefault();
        const { value } = event.target;
        const formErrors = { ...this.state.formErrors };

        switch (name) {
            case "name":
                formErrors.name = value.length < 1 ? "Name is required" : "";
                break;
            default:
                break;
        }
        this.setState({ formErrors, [name]: value });
    };

    submitForm = (e) => {
        e.preventDefault();

        if (!this.formValid()) {
            return
        }

        this.props.onSubmit(this.state.name, this.state.photoUrl);
        this.setState({name: "", photoUrl: ""});
        this.props.onClose();

    };



    render() {
        let {open, onSubmit, onClose} = this.props;
        const {name, photoUrl} = this.state;
        const setName = name => this.setState({name});
        const setPhotoUrl = photoUrl => this.setState({photoUrl});
        const { formErrors } = this.state;
        return (

            <Dialog open={open}
                    onClose={onClose}
                    fullWidth
                    maxWidth="sm">
                <DialogTitle>Dodaj grupę</DialogTitle>
                <DialogContent>
                    <img src={photoUrl || defaultPhotoUrl} className={css.photoImg}/>

                    <TextField
                        autoFocus
                        margin="dense"
                        id="add-grup-name"
                        label="Nazwa grupy"
                        type="text"
                        fullWidth
                        value={this.state.name}
                        onChange={this.handleChange('name')}
                    />
                    {formErrors.name.length > 0 && (
                        <span className={css.error}>{formErrors.name}</span>
                    )}
                    <TextField
                        margin="dense"
                        id="add-grup-photo"
                        label="Adres zdjęcia"
                        type="text"
                        fullWidth
                        value={photoUrl}
                        onChange={e => setPhotoUrl(e.target.value)}
                    />
                </DialogContent>

                <DialogActions>
                    <Button onClick={onClose} color="primary" variant="text">
                        Cofnij
                    </Button>
                    <Button onClick={this.submitForm} color="primary" disabled={!this.formValid()} variant="contained">
                        Dodaj
                    </Button>
                </DialogActions>
            </Dialog>

        );
    }
}

AddGroupDialog.propTypes = {
    open: PropTypes.any,
    onSubmit: PropTypes.any,
    onClose: PropTypes.any
}


export default AddGroupDialog;
