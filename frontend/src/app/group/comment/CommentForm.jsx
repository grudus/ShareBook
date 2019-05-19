import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import * as PropTypes from "prop-types";
import React, { Component } from 'react';


class CommentForm extends Component {

    state = {
        CommentText: ''
    };

    submitPost = (e) => {
        if (e && e.preventDefault)
            e.preventDefault();
        this.props.onSubmit(this.state.CommentText);
        this.setState({ CommentText: '' });
    };

    render() {
        const updateText = ({ target }) => {
            return this.setState({ CommentText: target.value });
        };

        return (
                    <form onSubmit={this.submitPost}>
                        <TextField
                            autoFocus
                            margin="dense"
                            type="text"
                            placeholder="Napisz komentarz..."
                            fullWidth
                            value={this.state.CommentText}
                            onChange={updateText}
                        />
                        <Button onClick={this.submitPost} color="primary" variant="contained">
                            Dodaj
                        </Button>
                    </form>
        );
    }

}
CommentForm.propTypes = {
    onSubmit: PropTypes.any,
};


export default CommentForm;
