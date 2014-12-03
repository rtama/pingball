board name=Mercury

# define some balls
ball name=Ball x=1.8 y=4.5 xVelocity=-3.4 yVelocity=-2.3 

# define some bumpers
squareBumper name=Square x=3 y=12
circleBumper name=Circle x=14 y=3
triangleBumper name=Tri x=1 y=1 orientation=270

# define an absorber to catch the ball
absorber name=Abs x=0 y=19 width=20 height=1 

# make the absorber self-triggering
fire trigger=Abs action=Abs 

# make two portals
portal name=Gamma x=12 y=2 otherPortal=Omega