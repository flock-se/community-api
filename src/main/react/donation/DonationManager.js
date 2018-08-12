import React from "react";
import {withStyles} from '@material-ui/core/styles';

import DonationTable from "./DonationTable";


const styles = theme => ({
  button: {
    position: 'fixed',
    right: 20,
    bottom: 20,
    margin: theme.spacing.unit,
  }
});

class DonationManager extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      donations: props.donations || []
    }

    this.rowClick = (user) => {
      console.log(user)
      this.setState({user})
    }

    fetch('/api/donations')
      .then(res => res.json())
      .then(json => {
        console.log(json)
        this.setState({donations: json});
      });

  }

  render() {

    const {classes} = this.props;

    return (

      <div>
        <DonationTable
          data={this.state.donations}
          handleRowClick={this.rowClick}
        />

      </div>
    )
  }
};

export default withStyles(styles)(DonationManager);