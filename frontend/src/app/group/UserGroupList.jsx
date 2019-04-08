import React from 'react';
import PropTypes from 'prop-types'
import { Link } from "react-router-dom";


const UserGroupList = ({ groups }) => (
    <ul>
        {groups.map(group => (
            <li key={group.id}>
                <Link to={`/groups/${group.id}`}>
                    {group.name}
                </Link>
            </li>
        ))}

    </ul>);


UserGroupList.propTypes = {
    groups: PropTypes.arrayOf(PropTypes.shape({
        name: PropTypes.string,
    })).isRequired
};
export default UserGroupList;
