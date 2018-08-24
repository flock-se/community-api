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
                <TableCell component="th" scope="row">
                  <Link to={`/members/${it.member.id}`}>{this.memberToName(it.member)}</Link>
                </TableCell>
                <TableCell>{it.frequency}</TableCell>
                <TableCell>{it.amount}</TableCell>
                <TableCell>
                  {status === 'PENDING' ?  <Chip label="Pending" style={{backgroundColor: 'orange'}}/> : null}
                  {status === 'ERROR' ?  <Chip label="Error" style={{backgroundColor: 'red'}}/> : null}
                  {status === 'SUCCESS' ?  <Chip label="Success" style={{backgroundColor: 'green'}}/> : null}


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
    if (member.infix) {
      return `${member.firstName} ${member.infix} ${member.surName}`
    }
    return `${member.firstName} ${member.surName}`
  }

  handleClick(event, user) {
    if (this.props.handleRowClick)
      return this.props.handleRowClick(event, user)
    console.log(user)
  }

}

export default DonationTable;