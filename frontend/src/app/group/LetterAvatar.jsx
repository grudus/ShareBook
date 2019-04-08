import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import deepOrange from '@material-ui/core/colors/deepOrange';
import deepPurple from '@material-ui/core/colors/deepPurple';
import Grid from '@material-ui/core/Grid';
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

const LetterAvatar = ({classes, name}) => (
        <div className={css.Avatar}>
            <Avatar className={classes.orangeAvatar}>{name[0]}</Avatar>
        </div>
    );

LetterAvatar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(LetterAvatar);
