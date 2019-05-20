import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import * as PropTypes from "prop-types";
import React, { Component } from 'react';
import css from './comment-form.scss';

class CommentForm extends Component {
    constructor(props) {
        super(props);
        this.state = {CommentText: ''};
        this.submitComment = this.submitComment.bind(this);
    }

    state = {
        CommentText: ''
    };

    submitComment = (e) => {
        if (e && e.preventDefault)
            e.preventDefault();
        //this.setState({value: this.state.CommentText});
        this.props.actionWhenAddComment(this.state.CommentText);
        this.setState({ CommentText: '' });
    };


    render() {
        const updateText = ({ target }) => {
            return this.setState({ CommentText: target.value });
        };

        return (
                    <form onSubmit={this.submitComment}>
                        <div className={css.addComments}>
                        <TextField
                            autoFocus
                            margin="dense"
                            type="text"
                            placeholder="Napisz komentarz..."
                            fullWidth
                            value={this.state.CommentText}
                            onChange={updateText}
                        />
                        <Button onClick={this.submitComment} type="submit" color="primary" variant="contained">
                            Dodaj
                        </Button>

                        </div>
                    </form>

        );
    }

}
CommentForm.propTypes = {
    actionWhenAddComment: PropTypes.any.isRequired,
};


export default CommentForm;
