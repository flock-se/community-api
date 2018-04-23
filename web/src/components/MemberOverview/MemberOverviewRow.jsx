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
      <MemberOverviewRowButton data={props.data} handleAction={props.handleAction}/>
    </TableRowColumn>
  </TableRow>
);

MemberOverviewRow.propTypes = {
  data: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    status: PropTypes.string.isRequired,
  }),
  handleAction: PropTypes.func,
};

export default MemberOverviewRow;
