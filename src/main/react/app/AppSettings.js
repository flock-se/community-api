import React from 'react';
import {withStyles} from "@material-ui/core/styles/index";

import {Link, Route} from 'react-router-dom'

import Grid from '@material-ui/core/Grid';

import Paper from '@material-ui/core/Paper';

import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import MemberGroupFeature from 'flock-eco-feature-member/member_group/MemberGroupFeature'

const styles = {}

class AppSettings extends React.Component {

  render() {
    return (
      <Grid
        container
        spacing={16}
        wrap="nowrap"
      >
        <Grid item style={{width: '250px'}}>
          <Paper>
            <List component="nav">

              <ListItem button>
                <Link to={`/settings/member_group`}>
                  <ListItemText primary="Member Groups"/>
                </Link>
              </ListItem>

              {/*<ListItem button>*/}
              {/*<ListItemText primary="Drafts" />*/}
              {/*</ListItem>*/}

            </List>
          </Paper>
        </Grid>

        <Grid item style={{width: '100%'}}>
          <Paper>

            <Route path='/' exact render={(props) => (
              <h1>Welcome</h1>
            )}/>

            <Route path='/settings/member_group' exact render={(props) => (
              <MemberGroupFeature/>
            )}/>

          </Paper>
        </Grid>
      </Grid>
    )
  }

}

export default withStyles(styles)(AppSettings);