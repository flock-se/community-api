import React from 'react';
import PropTypes from 'prop-types';
import {
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
import MemberOverviewRowButton from './MemberOverviewRowButton';

const MemberOverviewRow = props => (
  <TableRow>
    <TableRowColumn>{props.data.id}</TableRowColumn>
    <TableRowColumn>{props.data.name}</TableRowColumn>
    <TableRowColumn>{props.data.status}</TableRowColumn>
    <TableRowColumn>
      <MemberOverviewRowButton data={props.data} handleSave={props.handleSave}/>
    </TableRowColumn>
  </TableRow>
);

MemberOverviewRow.propTypes = {
  data: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    status: PropTypes.string.isRequired,
  }),
  handleSave: PropTypes.func,
};

export default MemberOverviewRow;
