import Avatar from '@material-ui/core/Avatar';
import deepOrange from '@material-ui/core/colors/deepOrange';
import deepPurple from '@material-ui/core/colors/deepPurple';
import { withStyles } from '@material-ui/core/styles';
import * as PropTypes from 'prop-types';
import React from 'react';
import css from './group.module.scss';

const styles = {
    avatar: {
        margin: 10,
    },
    orangeAvatar: {
        margin: 10,
        color: '#fff',
        backgroundColor: deepOrange[500],
    },
    purpleAvatar: {
        margin: 10,
        color: '#fff',
        backgroundColor: deepPurple[500],
    },
};

const LetterAvatar = ({ classes, text, className, ...props }) => (
    <div className={css.Avatar}>
        <Avatar className={classes.orangeAvatar + " " + className} {...props}>{text}</Avatar>
    </div>
);

LetterAvatar.propTypes = {
    text: PropTypes.string.isRequired,
};

export default withStyles(styles)(LetterAvatar);
