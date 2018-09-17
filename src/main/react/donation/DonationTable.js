import React from "react";

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

import Chip from '@material-ui/core/Chip';

import Paper from '@material-ui/core/Paper';

import {Link} from 'react-router-dom'

class DonationTable extends React.Component {


  render() {

    const {data} = this.props;

    return (
      <Paper>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Frequency</TableCell>
              <TableCell>Amount</TableCell>
              <TableCell>Date</TableCell>
              <TableCell>Status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>{data.map(it => {
            const status = it.transactions[0].status
            return (
              <TableRow
                key={it.id}
                hover
                onClick={event => this.handleClick(it)}
              >
                <TableCell component="th" scope="row">{this.memberToName(it.member)}</TableCell>
                <TableCell>{it.frequency}</TableCell>
                <TableCell>&euro; {it.amount},-</TableCell>
                <TableCell>{this.getFormattedDate(it.date)}</TableCell>
                <TableCell>
                  {status === 'PENDING' ?  <Chip label="Pending" style={{backgroundColor: '#ffd805', borderRadius: '0px', width: '80px'}}/> : null}
                  {status === 'ERROR' ?  <Chip label="Error" style={{backgroundColor: '#ff3366', borderRadius: '0px', width: '80px'}}/> : null}
                  {status === 'SUCCESS' ?  <Chip label="Success" style={{backgroundColor: '#7ed321', borderRadius: '0px', width: '80px'}}/> : null}
                </TableCell>
              </TableRow>
            )
          })}
          </TableBody>
        </Table>
      </Paper>
    )
  }

  memberToName(member) {
    if(!member)
      return 'Anonymous'
    if (member.infix) {
      return `${member.firstName} ${member.infix} ${member.surName}`
    }
    return `${member.firstName} ${member.surName}`
  }

  handleClick(event, user) {
    if (this.props.handleRowClick)
      return this.props.handleRowClick(event, user)
  }

  getFormattedDate(dateStr) {
    if(!dateStr) return;
    const date = new Date(Date.parse(dateStr))
    return date.toLocaleString();
  }

  desc(a, b, orderBy) {
    if (b[orderBy] < a[orderBy]) {
      return -1;
    }
    if (b[orderBy] > a[orderBy]) {
      return 1;
    }
    return 0;
  }

  getSorting(order, orderBy) {
    return order === 'desc' ? (a, b) => desc(a, b, orderBy) : (a, b) => -desc(a, b, orderBy);
  }

}

export default DonationTable;